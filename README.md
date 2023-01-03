## 재고시스템으로 알아보는 동시성이슈 해결방법 - 최상용

### Race Condition
두개 이상의 프로세스가 공통 자원을 병행적으로 쓰기 또는 읽기 작업을 하며 발생하는 문제이다.

### Race Condition 해결방안

**Application Level**

- `Synchronized` 키워드를 사용하여 하나의 메서드가 실행중일때는 접근하지 못하도록 막을 수 있다.

**Database Lock (MySql)**

- Pessimistic Lock
    - 실제로 데이터에 Lock 을 걸어 정합성을 맞추는 방법
    - exclusive lock 을 걸게되면 다른 트랜잭션에서는 lock 이 해체되기전에는 가져갈 수 없다.
- Optimistic Lock
    - 버전을 이용하여 정합성을 맞출 수 있다.
- Named Lock
    - 이름을 가진 matadata locking 방법
    - 이름을 가진 lock 을 획득한 후 해체될때까지 다른 세션은 이 lock 을 획득할 수 없도록한다.
    - 주의할점은 transaction 이 종료될 때 lock 이 자동으로 해체되지 않는다.