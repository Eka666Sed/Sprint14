
        class Scratch {

            public static void main(String[] args) {
                Example example = new Example();
                example.doWork();
            }

        }

        class Example {

            // Заводим целочисленный счётчик
            private int count = 0;

            // Метод, увеличивающий счётчик на единицу при каждом вызове
            public void increment() {this.count++;
            }

            public void doWork() {
                // Создаём два потока, каждый увеличивает общий счётчик
                // по 10 тысяч раз
                Thread thread1 = new Thread(
                        new Runnable() {
                            public void run() {
                                for (int i = 0; i < 10000; i++) {
                                    increment();
                                }
                            }
                        }
                );

                Thread thread2 = new Thread(
                        new Runnable() {
                            public void run() {
                                for (int i = 0; i < 10000; i++) {
                                    increment();
                                }
                            }
                        }
                );

                // Запускаем оба потока и ждём их завершения
                thread1.start();
                thread2.start();

                try {
                    thread1.join();
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Count is: " + count);
            }

        }