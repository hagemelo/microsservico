//'use strict';
const kafka = require('kafka-node')
const bp = require('body-parser')
const config = require('./config')
const respcod = require('../conf/response-code')
const {agendar_atendimento_error: AgendarAtendimentoError} = require('../exceptions')


class KafkaOff {
  //Classe para responder de modo padrao qdo o Kafka estiver desabilitado
  constructor (event) {
    this.event = event
  } 

  async run(){
    return respcod.responseCode.acceptedWithThismessageReturn(this.event, 'Nenhuma Acao Sera Tomada. Kafka Off')
  }

}

class KafkaOn {
  constructor (event) {
    this.event = event
  } 

  async run(){
    let Producer = kafka.Producer
    let client = new kafka.KafkaClient({kafkaHost: process.env.KAFKA_SERVER})
    let producer = new Producer(client)
    let kafka_topic = process.env.TOPIC_AGENDARATENDIMENTO


    let payloads =  [{ 
      topic: kafka_topic, 
      messages: this.event.body,
      partition: 0 
    }]

    producer.on('ready', async function () {

      let push_status = producer.send(payloads, (err, data) => {
        if (err) {
          console.error('[kafka-producer -> '+kafka_topic+']: broker failed')
          console.error(err)
          throw new AgendarAtendimentoError()
        }else {
          let bodyreturn = JSON.stringify(
            {
              topic: kafka_topic,
              message: 'kafka-producer -> broker success',
              input: this.event.body,
            },
            null,
            2
          )
          console.log('[kafka-producer -> '+kafka_topic+']: broker success')
          return respcod.responseCode.successWithThisBodyReturn(this.event, bodyreturn)
        }
      })


    })

    producer.on('error', function (err) {

      console.log(err)
    })
    
   
  }

}


module.exports.action = async event => {
  
  console.log(AgendarAtendimentoError)
  let runKafka = ((process.env.KAFKA_ENABLE === 'ON') ? new KafkaOn(event): new KafkaOff(event)) 
  return runKafka.run()
}






