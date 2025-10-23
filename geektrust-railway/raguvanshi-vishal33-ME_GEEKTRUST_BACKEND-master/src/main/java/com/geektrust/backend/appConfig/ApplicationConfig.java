// package com.geektrust.backend.appConfig;

// public class ApplicationConfig {
//     private final IQuestionRepository questionRepository = new QuestionRepository();
//     private final IUserRepository userRepository = new UserRepository();
//     private final IContestRepository contestRepository = new ContestRepository();

//     private final IQuestionService questionService = new QuestionService(questionRepository);
//     private final IUserService userService = new UserService(userRepository, contestRepository);
//     private final IContestService contestService = new ContestService(contestRepository, questionRepository, userRepository, userService);
    
//     private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
//     private final CreateQuestionCommand createQuestionCommand = new CreateQuestionCommand(questionService);
//     private final ListQuestionCommand listQuestionCommand = new ListQuestionCommand(questionService);
//     private final CreateContestCommand createContestCommand = new CreateContestCommand(contestService);
//     private final ListContestCommand listContestCommand = new ListContestCommand(contestService);
//     private final AttendContestCommand attendContestCommand = new AttendContestCommand(userService);
//     private final RunContestCommand runContestCommand = new RunContestCommand(contestService);
//     private final LeaderBoardCommand leaderBoardCommand = new LeaderBoardCommand(userService);
//     private final WithdrawContestCommand withdrawContestCommand = new WithdrawContestCommand(userService);

//     private final CommandInvoker commandInvoker = new CommandInvoker();

//     public CommandInvoker getCommandInvoker(){
//         commandInvoker.register("CREATE_USER",createUserCommand);
//         commandInvoker.register("CREATE_QUESTION",createQuestionCommand);
//         commandInvoker.register("LIST_QUESTION",listQuestionCommand);
//         commandInvoker.register("CREATE_CONTEST",createContestCommand);
//         commandInvoker.register("LIST_CONTEST",listContestCommand);
//         commandInvoker.register("ATTEND_CONTEST",attendContestCommand);
//         commandInvoker.register("RUN_CONTEST",runContestCommand);
//         commandInvoker.register("LEADERBOARD",leaderBoardCommand);
//         commandInvoker.register("WITHDRAW_CONTEST",withdrawContestCommand);
//         return commandInvoker;
//     }
// }
