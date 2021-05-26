    function connect(){
        let pseudo = document.getElementById("pseudo").value;
        if(pseudo.length > 0){
          Interface.setUserName(pseudo);
          window.location.replace("chatBot.html");
        } else {
          Interface.showToast();
        }
    }

    function disconnect(){
        window.location.replace("index.html");
        Interface.disconnect();
     }

    function loadConversation(){
       let conversation = Interface.getConversation();
       let obj = JSON.parse(conversation);
       for (i in obj) {
           showMessage(obj[i].message, obj[i].date.substring(10).slice(0, -3), obj[i].isBot);
       }
    }

    function sendMessage(){
       let userMessage = document.getElementById("message").value;
       if(userMessage.length > 0){
       let date = getDate();
       showMessage(userMessage, date.substring(10).slice(0, -3), false);
       Interface.saveMessage(userMessage, date, false);
       let botMessage = Interface.getBotResponse(userMessage);
       let dateBot = getDate();
       showMessage(botMessage, dateBot.substring(10).slice(0, -3), true);
       Interface.saveMessage(botMessage, dateBot, true);
       }
       document.getElementById("message").value = "";
       scrollChatDiv();
    }

    function showMessage(message, date, isBot){
       let divChatBox = document.getElementById("chatbox");
       let labelDate = document.createElement("label");
       let labelMessage = document.createElement("label");
       if(isBot){
           labelDate.style.cssText = 'margin-left:15px; font-size: 12px;float: left;';
           labelDate.innerHTML = Interface.getBotName().bold() + '<b> - </b>' + date.bold();
           labelMessage.style.cssText = 'margin-left: 10px; padding: 5px; background-color: #ffb366;border-radius: 10px;float: left;';
       } else {
           labelDate.style.cssText = 'margin-right: 15px; font-size: 12px;float: right;';
           labelDate.innerHTML = Interface.getUserName().bold() + '<b> - </b>' + date.bold();
           labelMessage.style.cssText = 'margin-right: 10px; padding: 5px; background-color: #ffcc99;border-radius: 10px;float: right;';
       }
       divChatBox.appendChild(labelDate);
       appendBr(divChatBox);
       labelMessage.innerHTML = message;
       divChatBox.appendChild(labelMessage);
       appendBr(divChatBox);
       appendBr(divChatBox);
    }

    function appendBr(div){
        let br = document.createElement("br");
        div.appendChild(br);
    }

    function clearConversation(){
       document.getElementById("chatbox").innerHTML = "";
       Interface.deleteMessages();
    }

    function scrollChatDiv(){
           let chatDiv = document.getElementById("chatbox");
           chatDiv.scrollTop = chatDiv.scrollHeight;
    }

    function getDate(){
        let today = new Date();
        let date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+ today.getDate();
        let time = today.getHours() + ":" + getFullMinutes(today) + ":" + getFullSeconds(today);
        let dateTime = date+' '+time;
        return dateTime;
    }

    function getFullMinutes(today){
        let minutes = today.getMinutes();
        if (minutes < 10) {
           return '0' + minutes;
       } else {
          return minutes;
       }
    }

    function getFullSeconds(today){
        let seconds = today.getSeconds();
        if (seconds < 10) {
           return '0' + seconds;
        } else {
          return seconds;
        }
     }