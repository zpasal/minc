func largestInColumn(a:int[10][10], n:int, large:int[10], value:int[10]) : void {
    var i : int;
    var j : int;
    i = 0;
    while(i < n) {
        large[i] = 0;
        value[i] = a[0][i];
        j = 1;
        while(j < n) {
            if (a[j][i] > value[i]) {
                value[i] = a[j][i];
                large[i] = j;
            }
            j = j + 1;
        }
        i = i + 1;
    }
}
