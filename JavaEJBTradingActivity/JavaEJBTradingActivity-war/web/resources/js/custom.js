/*Функция показа сообщения о процессе загрузки*/
function showProgress(data) {    
    if (data.status === "begin") {
        document.getElementById('loading_wrapper').style.display = "block";
    } else if (data.status === "success") {
        document.getElementById('loading_wrapper').style.display = "none";
    }
}
/* Функция приема уведомлений с сервера */
function socketListener(message, channel, event) {
    console.log(message);
    mojarra.ab(this,event,'click',0,'salesList',{'onevent':showProgress});
}
