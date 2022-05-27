var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
const db  = require('./db')
var app = express();
const scheduler = require('node-schedule')

require('dotenv').config()
const { Telegraf,  Scenes, session} = require('telegraf')

const bot = new Telegraf(process.env.BOT_TOKEN)
bot.use(require('./composers/start.composer'))
const stage = new Scenes.Stage([require('./scenes/commodity.scene')(bot)])
bot.use(session())
bot.use(stage.middleware())
bot.command('/launch',  ctx =>ctx.scene.enter('timer'))

bot.launch()


// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});


module.exports = {app};