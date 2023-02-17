package chap05.main;

import chap05.spring.*;
import chap05.config.AppCtx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForSpring {

    private static ApplicationContext ctx = null;

    public static void main(String[] args) throws IOException {
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while (true) {
            System.out.println("명령어를 입력하세요:");
            command = br.readLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            }
            if (command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            } else if (command.equalsIgnoreCase("list")) {
                processListCommand();
                continue;
            } else if (command.startsWith("info")) {
                processInfoCommand(command.split(" "));
                continue;
            } else if (command.equalsIgnoreCase("version")) {
                processVersionCommand();
                continue;
            }
            printHelp();
        }
    }
    private static void processNewCommand(String[] arg) {
        if (arg.length != 5) {
            printHelp();
            return;
        }
        MemberRegisterService regSvc = ctx.getBean(MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);

        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호-확인 불일치\n");
            return;
        }
        try {
            regSvc.regist(req);
            System.out.println("등록완료\n");
        } catch (DuplicateMemberException e) {
            System.out.println("이미 존재함\n");
        }
    }

    private static void processChangeCommand(String[] arg) {
        if (arg.length != 4) {
            printHelp();
            return;
        }
        ChangePasswordService changePwdSvc = ctx.getBean(ChangePasswordService.class);
        try {
            changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호 변경 완료\n");
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않음\n");
        } catch (WrongIdPasswordException e) {
            System.out.println("이메일 또는 암호 불일치\n");
        }
    }

    private static void processListCommand() {
        MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
        listPrinter.printAll();
    }

    private static void processInfoCommand(String[] arg) {
        if (arg.length != 2) {
            printHelp();
            return;
        }
        MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
        infoPrinter.printMemberInfo(arg[1]);
    }

    private static void processVersionCommand() {
        VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }

    private static void printHelp() {
        System.out.println();
        System.out.println("잘못된 명령입니다.");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재비번 변경비번");
        System.out.println();
    }
}
