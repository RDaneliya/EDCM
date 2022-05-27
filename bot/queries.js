const scheduler = require('node-schedule')

const createJob = async function(bot, chatid) {
    var date = new Date()
    console.log((date.getSeconds() - date.getSeconds() % 59) / 59);
    return new scheduler.scheduleJob(`${(date.getSeconds()+1)%60} ${date.getMinutes()%10 + (date.getSeconds() - date.getSeconds() % 59) / 59}/10 * * * *`, async function() {
        // const user = await db.User.findOne({chat_id: chatid});
        console.log(new Date())
        await bot.telegram.sendMessage(chatid, 'Hello World');
    });
}


module.exports = {createJob}