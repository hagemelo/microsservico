const bp = require('body-parser')
const respcod = require('../conf/response-code')
const {PushTopicError: commandException} = require('../exceptions/exception')


class KafkaOff {
    //Classe para responder de modo padrao qdo o Kafka estiver desabilitado
    constructor (event) {
      this.event = event
    } 
  
    run(){
      
      return respcod.responseCode.acceptedWithThismessageReturn(this.event, 'Nenhuma Acao Sera Tomada. Kafka Off')
    }
  
}


class KafkaOn {
  
    constructor (event, commandtopic) {
      this.event = event
      this.commandtopic = commandtopic  
    } 
    
    criarmsg(){
      return  [{ 
        topic: this.commandtopic, 
        messages: this.event.body,
        partition: 0 
      }] 
    }
  
    pushTopic(producer, payloads){
      
      producer.on('ready', function () {
        
        producer.send(payloads, (err, data) => {
          if (err) {
            //console.error('[kafka-producer -> '+kafka_topic+']: broker failed')
            console.error('[kafka-producer -> '+payloads.topic+']: broker failed')
            console.error(err)
            
            //throw new AgendarAtendimentoError()
          }else {
            
            //console.log('[kafka-producer -> '+kafka_topic+']: broker success')
            console.log('[kafka-producer -> '+payloads.topic+']: broker success')
                      
          }
        })
  
      })
      producer.on('error', function () {
        throw new commandException("Erro in (" +payloads.topic + ")")
      })
    }

    
  }

  module.exports = {
    KafkaOff,
    KafkaOn
  }