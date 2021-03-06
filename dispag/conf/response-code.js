//#200
const OK = 200
const CREATED = 201
const ACCEPTED = 202
//#400
const BAD_REQUEST = 400
const UNAUTHORIZED = 401
const FORBIDDEN = 403



let responseCode = {

    successDefault : function (event){
        return {
            statusCode: OK,
            body: JSON.stringify(
              {
                message: 'Operacao Realizada Com Sucesso!',
                input: event,
              },
              null,
              2
            ),
          }
    },

    successWithThisBodyReturn : function (event, pbody){
        
      return {
            statusCode: OK,
            body: pbody,
          }
    },

    acceptedWithThismessageReturn : function (event, msg){
        return {
            statusCode: ACCEPTED,
            body: JSON.stringify(
                {
                  message: msg,
                },
                null,
                2
              ),
          }
    },

    tokenNaoAutorizadoReturn : function (event){
        
      return {
            statusCode: UNAUTHORIZED,
            body: JSON.stringify(
              {
                auth: false,
                message: 'Falha ao autenticar o token'
              },
              null,
              2
            ),
          }
    },

    ausenciaHeadersFundamentaisReturn : function (event){
        
      return {
            statusCode: UNAUTHORIZED,
            body: JSON.stringify(
              {
                auth: false,
                message: 'Ausencia de Headers Fundamentais para Requisicao'
              },
              null,
              2
            ),
          }
    },




}



module.exports = {
    responseCode
}
