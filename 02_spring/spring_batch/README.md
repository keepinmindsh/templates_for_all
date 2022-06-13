# Job Launcher 

- 배치 Job을 실행시키는 역할을 한다.
- jobLauncher.run(job, jobParameter); 로직으로 배치를 수행한다. job, jobParameter 를 인자로 받아서 jobExecution을 결과로 반환한다.
- 스프링 배치가 실행되면 jobLauncher 빈을 생성하고, jobLauncherApplicationRunner가 자동적으로 jobLauncher을 실행시킨다.

> [Job Launcher] ( https://opennote46.tistory.com/76)  
> [Job Launcher] (https://devfunny.tistory.com/688)
