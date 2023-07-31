//要素の取得を行う
let label = document.getElementById("label");
let button1 = document.getElementById("change-text1");
let button2 = document.getElementById("change-text2");
let button3 = document.getElementById("change-text3");

//リンゴに変更をクリックしたら初期表示を変更する
button1.addEventListener('click', () => {
    label.innerHTML = "リンゴ";
});

//バナナに変更
button2.addEventListener('click', () => {
    label.innerHTML = "バナナ";
});

//ミカン
button3.addEventListener('click', () => {
    label.innerHTML = "ミカン";
});