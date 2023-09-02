
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