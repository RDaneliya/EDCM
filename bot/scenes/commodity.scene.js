let axios = require('axios')
const {createJob} = require('../queries')
// const createJob = require('../app').createJob()
const Scenes = require('telegraf').Scenes


module.exports = (bot) => new Scenes.WizardScene(
        'timer',
        async (ctx) =>{
            try {
                await ctx.replyWithHTML('Please specify commodities')
                return ctx.wizard.next()
            } catch {}
        },
        async (ctx) =>{
                let commodity = ctx.message.text
                ctx.scene.session.commodity = commodity
                let url =`http://localhost:8080/api/commodities/price/${commodity}`
                try{
                    let result = await axios.get(url)
                    ctx.scene.session.price = result.data.maxSellPrice
                    await ctx.replyWithHTML('Please specify price')
                    return ctx.wizard.next()
                }
                catch(e){
                    await ctx.replyWithHTML(`Sorry ${commodity} doesn't exist`)
                    return ctx.scene.leave()
            }

        },
    async (ctx) =>{
        try {
            let price = ctx.message.text
            console.log(price, ctx.scene.session.price)
            if (price<=ctx.scene.session.price){
                let chatId= ctx.message.chat.id
                console.log()
                await createJob(bot, chatId)
            }
            else {
                await ctx.replyWithHTML(`Your price unreal MotherFucker!!!`)
            }
            return ctx.scene.leave()
        } catch(e) {console.log("Find error", e)}
    })

