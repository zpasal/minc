package ba.compiler.minc.flowgraph;

import ba.compiler.minc.basicblocks.BasicBlock;
import ba.compiler.minc.intercode.instructions.QuadCode;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.ComponentNameProvider;
import org.jgrapht.io.DOTExporter;
import org.jgrapht.io.GraphExporter;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlowGraphBuilder {
    private DefaultDirectedGraph<BasicBlock, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

    public FlowGraphBuilder() {
    }

    public void build(List<BasicBlock> basicBlocks) {
        Map<Integer, BasicBlock> basicBlockMap = new HashMap<>();

        basicBlocks.stream().forEach(bb -> {
            basicBlockMap.put(bb.getName(), bb);
            graph.addVertex(bb);
        });

        // Iterate through all Basic-blocks - last instruction is jump to other blocks
        for (int i=0; i< basicBlocks.size(); i++) {
            BasicBlock bb = basicBlocks.get(i);
            assert bb.getInstructions().size() != 0 : "Basic block with 0 instructions not allowed";
            QuadCode inst = bb.getInstructions().get(bb.getInstructions().size()-1);

            if (inst.isConditional()) {
                List<Integer> targets = inst.getConditionalTargets();
                if (targets != null) {
                    targets.stream().forEach(name -> {
                        BasicBlock jumpToBasicBlock = basicBlockMap.get(name);
                        graph.addEdge(bb, jumpToBasicBlock);
                    });
                }
            }
            else {
                // link to next
                if (i + 1 < basicBlocks.size()) {
                    BasicBlock jumpToBasicBlock = basicBlocks.get(i + 1);
                    graph.addEdge(bb, jumpToBasicBlock);
                }
            }
        }
    }

    public void visualize() throws Exception {
        // use helper classes to define how vertices should be rendered,
        // adhering to the DOT language restrictions
        ComponentNameProvider<BasicBlock> vertexIdProvider = new ComponentNameProvider<BasicBlock>()
        {
            public String getName(BasicBlock bb)
            {
                return "BB_" +bb.getName();
            }
        };
        ComponentNameProvider<BasicBlock> vertexLabelProvider = new ComponentNameProvider<BasicBlock>()
        {
            public String getName(BasicBlock bb)
            {
                return bb.toString().replaceAll("\\R", "\\\\l");
            }
        };
        GraphExporter<BasicBlock, DefaultEdge> exporter =
                new DOTExporter<>(vertexIdProvider, vertexLabelProvider, null);
        Writer writer = new StringWriter();
        exporter.exportGraph(graph, writer);
        System.out.println(writer.toString());

    }
}
