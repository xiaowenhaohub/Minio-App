function changeSize(size) {
    var num;
    if ((size / 1024) <= 1024) {
        num = size / 1024;
        return num.toFixed(2) + 'KB';
    }
    if ((size / 1024) > 1024 && (size / 1024) <= (1024 * 1024)) {
        num = (size / 1024) / 1024;
        return num.toFixed(2) + 'M';
    }
    if ((size / 1024) > (1024 * 1024)) {
        num = ((size / 1024) / 1024) / 1024
        return num.toFixed(2) + 'G';
    }
}
export {
    changeSize
}