//'use strict';
const kafka = require('kafka-node');
const bp = require('body-parser');
const config = require('./config');

module.exports.action = async event => {
  
   
  
  try{
    const Producer = kafka.Producer
    //const client = new kafka.Client(config.kafka_server);
    const client = new kafka.KafkaClient({kafkaHost: '172.25.31.112:9092'})
    const producer = new Producer(client)
    const kafka_topic = 'agendaratendimentotopic'
    console.log(kafka_topic)

    let payloads =  [{ 
      topic: 'agendaratendimentotopic', 
      messages: '{"prontuario":"9966", '
          + '"hora":"H1315",'
          + '"obs":"sn",'
          + '"cliente":{'
            + '"nome": "Fulano de Tal",'
            + '"apelido": "Fulano",'
            + '"endereco": "Av teste de tes sn",'
            + '"contato" : "11 978452123"'
            + '},'
          + '"profissional":{'
            + '"nome": "Dr Cirqueira nano",'
            + '"cro": "45859",'
            + '"especialidade": "Tirar Dentes"'
          + '},'
          + '"plano": "AMIL_DENTAL",'
          + '"status": "AGENDADO"}',
          partition: 0 
      }]
    producer.on('ready', async function () {

      let push_status = producer.send(payloads, (err, data) => {
        if (err) {
          console.log('[kafka-producer -> '+kafka_topic+']: broker failed');
          console.log(err)

        }else {
          console.log('[kafka-producer -> '+kafka_topic+']: broker success');
        }
      })


    });

    producer.on('error', function (err) {

      console.log(err);
    })
  }
  catch (exception) {
    console.log(exception);
  }
  
  
  return {
    statusCode: 200,
    body: JSON.stringify(
      {
        message: 'Go Serverless v1.0! Your function executed successfully!',
        input: event,
      },
      null,
      2
    ),
  };

  // Use this code if you don't use the http event with the LAMBDA-PROXY integration
  // return { message: 'Go Serverless v1.0! Your function executed successfully!', event };
};






