var DataSource = EventSupport.extend({

    next: function () {
        if (this.array) {
            return this.array[this.index++];
        }
    },

    hasNext: function () {
        return this.array && this.array.length > this.index;
    },

    setArray: function (array) {
        this.array = array;
        this.index = 0;

    }
});