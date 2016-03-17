var Backbone = require('backbone');
var _ = require('underscore');
var tmpl = require('./templates');

module.exports = Backbone.View.extend({
  tagName: 'article',
  template: _.template(tmpl.post),
  initialize: function (){
    this.listenTo(this.model, 'change', this.render);
  };
  render: function (){
    var markup = this.template(this.model.toJSON());
    this.$el.html(markup);
    return this;
  }
})
