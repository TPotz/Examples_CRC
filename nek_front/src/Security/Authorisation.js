import { bake_cookie, read_cookie, delete_cookie } from 'sfcookies';
import React, { useEffect, useState } from 'react';
const loginUser = 'loginUser';


export var TrenutniKorisnik = (function() {


  
    var getName = function() {
      return read_cookie(loginUser);
    };
  
    var setName = function(name) { 
      isSubscribed(name);     
        
    };

    var isLogged = function() { 
        return read_cookie(loginUser).length > 0;     
      };

    var isSubscribed = function(name) { 
      //const [isSubscribed, setIsSubscribed] = useState(false);

      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ user: name })
    };  
  
      fetch('api/isUserSubscribed', requestOptions)
      .then(response => response.json())
      .then(data => {
        if(data) {
          bake_cookie(loginUser, name);
        window.location.reload(false);
      }});  
           
    };
  
    return {
      getName: getName,
      setName: setName,
      isLogged: isLogged,
      isSubscribed: isSubscribed
    }
  
  })();
  
;