'use strict';
var jwt = require('jsonwebtoken') // package jwt 
    ,config = require('./config.js') // arquivo com chave do token

const user = 'Alex'
const senha = 'MTIzNA=='


module.exports.login = async event => {
  
  const data = JSON.parse(event.body);


  console.log(data)
  var token = jwt.sign({ id: user, senha: senha }, config.secret, {
						 expiresIn: 86400 // validade do token, 24hrs
						});
  
  return {
    statusCode: 200,
    body: JSON.stringify(
      {
        authentication: true,
		user: user,
        token: token
      },
      null,
      2
    ),
  };
};

module.exports.verificartk = async event => {
  
  const token  = event.headers.token
  if (!token) 
    return res.status(403).send({ auth: false, message: 'Informe um token' });

  jwt.verify(token, config.secret, function(err, decoded) {      
    if (err) 
      return res.status(500).send({ auth: false, message: 'Falha ao autenticar o token' });    

   
  });

  return {
    statusCode: 200,
    body: JSON.stringify(
      {
        tokenOK: true,
		
      },
      null,
      2
		),
	};
  
  
};
