//'use strict';
const kafka = require('kafka-node')
const {KafkaOn: KafkaOn} = require('./command')
const {KafkaOff: KafkaOff} = require('./command')
const respcod = require('../conf/response-code')

const {AusenciaHeadersFundamentaisError: AusenciaHeadersFundamentaisError} = require('../exceptions/exception')
const {TokenExpiradoError: TokenExpiradoError} = require('../exceptions/exception')



var jwt = require('jsonwebtoken')



class KafkaOnNovoDebito extends KafkaOn {
  
  constructor (event, commandtopic) {
    super(event, commandtopic)
   
  } 
  
  run(){
    
    try{
      const client = new kafka.KafkaClient({kafkaHost: process.env.KAFKA_SERVER})
      let producer = new kafka.Producer(client)     
      let payloads = this.criarmsg()
      this.pushTopic(producer, payloads)
    }catch(exception){
      console.log("Entrou na Exception--> " + exception)
      //throw new AgendarAtendimentoError("Erro kafka")
    }  
    return respcod.responseCode.acceptedWithThismessageReturn(this.event, 'Operacao Realizada Com Sucesso, as acoes serão tomadas no decorrer do tempo')
  }
  
}

function validarExistenciaheader(event){

  if(!event.headers.token && !event.headers.uuid) 
    throw new AusenciaHeadersFundamentaisError("Ausencia dos headers uuid e token")
  
  console.log("uuid::" + event.headers.uuid)
}

function validarTokenExpirado(event){

  jwt.verify(event.headers.token, process.env.SECRET, function(err, decoded) {      
    if (err) 
      throw new TokenExpiradoError("Token Expirado")  
  });

  console.log("Token::" + event.headers.token)
}

export async function action(event) {
  try{
    validarExistenciaheader(event)
    validarTokenExpirado(event)
    let runKafka = ((process.env.KAFKA_ENABLE == 'ON') ? new KafkaOnNovoDebito(event, process.env.KAFKATOPIC_NOVODEBITO): new KafkaOff(event)) 
    return runKafka.run()
  }catch (exception) {

     console.info("Teste::::::::::::::" + exception.prototype)
  
    if (exception instanceof AusenciaHeadersFundamentaisError){
        
        return respcod.responseCode.ausenciaHeadersFundamentaisReturn(event)
        // be mean and gruff; 
        //break;
      }

    if (exception instanceof TokenExpiradoError) {
        // be mean and gruff; 
        console.info('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Token Expirado!!!!!')
        return respcod.responseCode.tokenNaoAutorizadoReturn(event)
      }
    


  }



}

