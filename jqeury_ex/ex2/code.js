$(function () {
    //<ol>の要素の取得
    let list = $("#list");
    //新しい<a>要素を追加
    let newLink = $("<a>");
    //aタグの中に内容を追加
    newLink.html("developer.mozilla.org");
    //リストの末尾に追加
    list.append(newLink);

    //新しいliをつくってaタグを入れる
    let newItem = $("<li>");
    list.append(newItem);
    newItem.append(newLink);

    // 新しい <p> 要素の作成
    let newText = $("<p>");
    newText.html("And more...");

    // リストの下に挿入
    list.after(newText);

    //要素を丸ごと削除
    //list.remove();

    //要素の中を空にするempty
    list.empty();

    //クリックイベントjs onclick
    $("#button1").click(()=>{
        console.log("hello");
    })
    //クリックイベント　addEvent~
    $("#button1").on("click",()=>{
        console.log("Goodbye");
    })
    $("#button1").on("click",()=>{
        console.log("Goodbye2");
    })
});