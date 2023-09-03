
import 'dart:svg';

void main() {
  print("Hello World");
}

void declareVar(){
  var name = '내꼬';

  // MARK 동작하지 않음 - var의 특징
  //name = 1

  String nameWithType = '너꺼?';

}

void dynamicVariable() {
  dynamic name; // MARK 가능하다면 사용을 피해야함!

  if(name is String){
    if ( name.isEmpty ) {
      name = '내꼬';
    }
  }

  if ( name is Number) {
    if ( name == 0 ) {
      name = 10000;
    }
  }

  print(name);
}

void nullVariable() {
  String? name = 'Null';
  name = null;

  if ( name != null ){
    if ( name.isEmpty ) {

    }
  }

  name?.isEmpty; // MARK: - null 이 아닐 때만 값을 준다
}

void finalVariable() {
  final name = 'howard';
  // name = 'changed'; // MARK - cannot modify name
}

void lateVariable() {
  late final String name;

  name = 'Test';

  //name  = 'sadfasf'; // MARK 늦은 초기화 이후에 추가로 설정할 수 없음
}

void constantVariable() {
  const name = 'mine';  // MARK 컴파일 타임에 값을 확인할 수 있음, 컴파일 타임이 이미 값을 할 수 있는 것!

}

void dataTypes() {
  String name = 'asdfsadf';
  bool allive  = true;
  int age = 123;
  double money = 69.99;
  num x = 1123;
}

void ListSample(){
  var numbers = [0 ,1,2,3,4,5];

  var isPass = true;

  List<int> numbers2 = [
    1,
    2,
    3,
    4,
    if ( isPass ) 6,
  ];

  numbers2.add(5);
  
  print(numbers2);
}

void stringInterporation(){
  var name = 'howard';
  var age = 40;
  var greeting = 'Hi! $name, nice to meet you, I am ${age  + 20}';

  print(greeting);

}

void collectionFor() {
  var oldFriends = [ 'howard', 'wood' ];
  var newFriends = [
    'thomas',
    'kyle',
    for ( var friend in oldFriends) "$friend",
  ];

  print(newFriends);
}


void maps() {
  var player = {
    'name' : 'howard',
    'xp' : 19.99
  };

  Map<int, bool> playerStatus = {
    1 :  true,
    2 : false
  };

  Map<List<int>, bool > players = {
    [1,2,3,4] : true,
  };
}

void sets() {
  Set<int> numbers = { 1,2,3,4,5 }; // MARK 중복을 허용하지 않음!

  numbers.add(1);
  numbers.add(1);
  numbers.add(1);
  numbers.add(1);

  print(numbers);
}

void sayHello(String name) {
  print("Hello $name nice to meet you");
}

String sayHelloReturn(String name) {
  return "Hello $name nice to meet you";
}

String sayHelloReturnWithout(String name) => "Hello $name nice to meet you"; // MARK Arrow Function

String sayHelloWithNamed({String name = "Hello", int age = 99,required String country}){  // Mark Named Parameter ( Named Parameter, Default, Required )
  return "$name, $age $country";
}

void ClientCallSayHello() {
  sayHelloWithNamed(
    name : "Howard",
    country: "germany",
    age: 99,
  );
}

String sayHelloOptinal(String name, int age, [String? country = "cuba"]) => "$name, $age, $country";

void ClientCallOptinal(){
  sayHelloOptinal("good", 99);
}

String QQParameter1(String? name) => name != null ? name.toUpperCase() : 'ANON';

String QQParameter2(String? name) => name?.toUpperCase() ?? 'ANON';


void ClientCallQQ(){
  String? name;
  name ??= 'Nico';
  name = null;
  name ??= 'another';

}

typedef ListOfInts = List<int>;

List<int> reverseListOfNumbers(ListOfInts list) {
  var reversed = list.reversed;
  return reversed.toList();
}

void ClientCallTypeDef() {
  reverseListOfNumbers([1,2,3]);
}

class Player {
  String name = 'my';
  int xp = 1500;

}

void ClientCallClass(){
  var player = Player();

  print(player.name);
}

class Player1 {
  final String name = 'my';
  int xp = 1500;

  void sayHello() {
    print("Hi my name is $name");
  }
}

void ClientCallClass2() {
  var player = Player1();

}

class Player2 {
  late final String name;
  late int xp;

  Player2(String name, int xp){
    this.name = name;
    this.xp = xp;
  }

  void sayHello() {
    print("Hi my name is $name");
  }
}

void ClientCallClass3() {
  var player1 = Player2("asdfsafd", 123);


  var player2 = Player2("steadfast", 123123);

}


class Player3 {
  final String name;
  int xp;

  Player3(this.name, this.xp);

  void sayHello() {
    print("Hi my name is $name");
  }
}


void ClientCallClass4() {
  var player1 = Player3("asdfsafd", 123);


  var player2 = Player3("steadfast", 123123);

}


class PlayerNamed {
  final String name;
  int xp;
  String team;
  int age;

  PlayerNamed({
    required this.name,
    required this.xp,
    required this.team,
    required this.age
  });

  void sayHello() {
    print("Hi my name is $name");
  }
}


void ClientCallClassNamed() {
  var player1 = PlayerNamed(
    name : "my",
    xp: 1200,
    team: 'red',
    age: 12
  );


  var player2 = PlayerNamed(
      name : "my",
      xp: 1200,
      team: 'blue',
      age: 12
  );
}



class PlayerNamedConstruct {
  final String name, team;
  int xp, age;

  PlayerNamedConstruct({
    required this.name,
    required this.xp,
    required this.team,
    required this.age
  });

  PlayerNamedConstruct.createBluePlayer({
    required String name,
    required int age
  }) :
      this.age = age,
      this.name = name,
      this.team = 'blue',
      this.xp = 0;

  PlayerNamedConstruct.createRedPlayer({
    required String name,
    required int age
  }) :
        this.age = age,
        this.name = name,
        this.team = 'blue',
        this.xp = 0;

  void sayHello() {
    print("Hi my name is $name");
  }
}


void ClientCallClassNamedConstruct() {
  var player1 = PlayerNamedConstruct.createBluePlayer(name: 'my', age: 12);


  var player2 = PlayerNamedConstruct.createRedPlayer(name: 'my', age: 12);
}



