//メソッドその1
function printHello(){
    console.log("hello");
}
//メソッドの呼び出し
printHello();

//メソッド２returnあり
function sum(a,b){
    return a + b;
}
//メソッドの呼び出し
console.log(sum(1,2));

//デフォルトパラメータ
function print(str1,str2="世界",str3="!!!!"){
    console.log(str1 +" "+str2 +" "+str3);
}
//呼び出し
print("ヤッホー");
print("ヤッホー","お昼寝したい");
print("ヤッホー","お昼寝したい","おやつも食べたい");

//ラムダ式
let add =(x,y)=>x+y;
console.log(add(2,3));