var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
const db  = require('./db')
var app = express();
require('dotenv').config()
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

db.query("SELECT table_name FROM information_schema.tables\n" +
    "WHERE table_schema NOT IN ('information_schema','pg_catalog');", (err,res)=>{
  console.log(err,res)
})



// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
