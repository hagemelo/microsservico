

let responseCode = {

    successDefault : function (event){
        return {
            statusCode: 200,
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
            statusCode: 200,
            body: pbody,
          }
    },

    acceptedWithThismessageReturn : function (event, msg){
        return {
            statusCode: 202,
            body: JSON.stringify(
                {
                  message: msg,
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
