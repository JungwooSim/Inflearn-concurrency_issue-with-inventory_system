## 재고시스템으로 알아보는 동시성이슈 해결방법 - 최상용

### Race Condition
두개 이상의 프로세스가 공통 자원을 병행적으로 쓰기 또는 읽기 작업을 하며 발생하는 문제이다.

### Race Condition 해결방안

**Application Level**

- `Synchronized` 키워드를 사용하여 하나의 메서드가 실행중일때는 접근하지 못하도록 막을 수 있다.