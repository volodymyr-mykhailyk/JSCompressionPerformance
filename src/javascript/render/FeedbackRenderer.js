function FeedbackRenderer(renderer, callback) {
    this.render = function(results) {
        renderer.render(results);
        callback();
    }
}