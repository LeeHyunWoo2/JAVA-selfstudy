package ex03game.service;

import java.util.Scanner;

public class LoadingBar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int A = 0;  // 초기값 0
        int max = 10000;  // 최대값 10000
        int step = 200;  // 1칸당 200

        while (A < max) {
            // 현재 게이지를 출력
            printGauge(A, max, step);

            // 사용자로부터 값을 입력받아 A에 추가
            System.out.print("\nEnter a value to add to A: ");
            int value = scanner.nextInt();
            A += value;
        }

        // 최종 게이지 출력 및 종료 메시지
        printGauge(A, max, step);
        System.out.println("\nLoading complete!");
        scanner.close();
    }

    // 현재 A 값을 기반으로 게이지를 출력하는 메소드
    private static void printGauge(int current, int max, int step) {
        int gaugeLength = max / step;  // 게이지의 총 길이
        int filledLength = current / step;  // 채워진 게이지 길이

        // 게이지 문자열 생성
        String gauge = "[";
        for (int i = 0; i < gaugeLength; i++) {
            if (i < filledLength) {
                gauge += "#";
            } else {
                gauge += " ";
            }
        }
        gauge += "]";

        // \r을 사용하여 해당 줄을 갱신
        System.out.print("\r" + gauge);
    }
}
