function clickBtn()
{
    var e= document.getElementById('aa');
    e.innerHTML="SUCCESS";

    var inp = document.getElementById('name').value;
    document.getElementById("result").innerText = inp;
 }

//입력할 때마다 실행되는 거
function search_key()
{
    var inp = document.getElementById('name').value;
    document.getElementById("result").innerText = inp;
}