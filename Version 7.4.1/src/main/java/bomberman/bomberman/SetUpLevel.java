package bomberman.bomberman;

import java.util.concurrent.ThreadLocalRandom;

public class SetUpLevel {
    public static int getCountEnemy(int level) {
        int Enemy = 2;
        if (level >= 1 && level <= 10) {
            Enemy = ThreadLocalRandom.current().nextInt(2, 5);
        } else if (level >= 11 && level <= 20) {
            Enemy = ThreadLocalRandom.current().nextInt(5, 8);
        } else if (level >= 21 && level <= 30) {
            Enemy = ThreadLocalRandom.current().nextInt(8, 13);
        } else if (level >= 31 && level <= 40) {
            Enemy = ThreadLocalRandom.current().nextInt(12, 17);
        } else if (level >= 41 && level <= 50) {
            Enemy = ThreadLocalRandom.current().nextInt(16, 24);
        }
        return Enemy;
    }

    public static int getCountWall(int level) {
        int Wall = 50;
        if (level >= 1 && level <= 10) {
            Wall = 70;
        } else if (level >= 11 && level <= 20) {
            Wall = 50;
        } else if (level >= 21 && level <= 30) {
            Wall = 40;
        } else if (level >= 31 && level <= 40) {
            Wall = 40;
        } else if (level >= 41 && level <= 50) {
            Wall = 40;
        }
        return Wall;
    }

    public static int getCountBrick(int level) {
        int Brick = 50;
        if (level >= 1 && level <= 10) {
            Brick = 50;
        } else if (level >= 11 && level <= 20) {
            Brick = 80;
        } else if (level >= 21 && level <= 30) {
            Brick = 100;
        } else if (level >= 31 && level <= 40) {
            Brick = 80;
        } else if (level >= 41 && level <= 50) {
            Brick = 90;
        }
        return Brick;
    }

    public static int getDistance(int level) {
        int Distance = 5;
        if (level >= 1 && level <= 10) {
            Distance = 7;
        } else if (level >= 11 && level <= 20) {
            Distance = 5;
        } else if (level >= 21 && level <= 30) {
            Distance = 5;
        } else if (level >= 31 && level <= 40) {
            Distance = 4;
        } else if (level >= 41 && level <= 50) {
            Distance = 3;
        }
        return Distance;
    }

    public static int getNumAIEnemy(int level) {
        int AIEnemy = 1;
        if (level >= 1 && level <= 10) {
            AIEnemy = 1;
        } else if (level >= 11 && level <= 20) {
            AIEnemy = 2;
        } else if (level >= 21 && level <= 30) {
            AIEnemy = 3;
        } else if (level >= 31 && level <= 40) {
            AIEnemy = 4;
        } else if (level >= 41 && level <= 50) {
            AIEnemy = 4;
        }
        return AIEnemy;
    }

    public static int Percentage(int level, int Enemy) {
        int[] arr = new int[5];
        /** 1 : Beast. */
        /** 2 : Snake. */
        /** 3 :      . */
        if (level <= 10) {
            int count = 100;
            while (count > 0) {
                int type = ThreadLocalRandom.current().nextInt(1, 3);
                if (type == 1 || type == 2) {
                    count--;
                    arr[type]++;
                    if (arr[1] >= (15 + (level + 1) % 10 * 5 + Enemy * 5)) return 1;
                    if (arr[2] >= (25 + (level + 1) % 10 * 5 + Enemy * 5)) return 2;
                }
            }
            return 2;
        }
        if (level <= 20) {
            int count = 140;
            arr[1] = arr[2] = arr[3] = 0;
            while (count > 0) {
                int type = ThreadLocalRandom.current().nextInt(1, 4);
                if (type == 1 || type == 2 || type == 3) {
                    count--;
                    arr[type]++;
                    if (arr[1] >= (10 + (level + 1) % 10 * 2 + Enemy)) return 1;
                    if (arr[2] >= (10 + (level + 1) % 10 * 2 + Enemy)) return 2;
                    if (arr[3] >= (15 + (level + 1) % 10 * 2 + Enemy)) return 3;
                }
            }
        }
        if (level <= 30) {
            int count = 160;
            arr[1] = arr[2] = arr[3] = 0;
            while (count > 0) {
                int type = ThreadLocalRandom.current().nextInt(1, 4);
                if (type == 1 || type == 2 || type == 3) {
                    count--;
                    arr[type]++;
                    if (arr[1] >= (15 + (level + 1) % 10 * 2 + Enemy)) return 1;
                    if (arr[2] >= (10 + (level + 1) % 10 * 2 + Enemy)) return 2;
                    if (arr[3] >= (10 + (level + 1) % 10 * 2 + Enemy)) return 3;
                }
            }
        }
        if (level <= 40) {
            int count = 160;
            arr[1] = arr[2] = arr[3] = 0;
            while (count > 0) {
                int type = ThreadLocalRandom.current().nextInt(1, 4);
                if (type == 1 || type == 2 || type == 3) {
                    count--;
                    arr[type]++;
                    if (arr[1] >= (20 + (level + 1) % 10 * 2 + Enemy)) return 1;
                    if (arr[2] >= (15 + (level + 1) % 10 * 2 + Enemy)) return 2;
                    if (arr[3] >= (10 + (level + 1) % 10 * 2 + Enemy)) return 3;
                }
            }
        }
        if (level <= 50) {
            int count = 150;
            arr[1] = arr[2] = arr[3] = 0;
            while (count > 0) {
                int type = ThreadLocalRandom.current().nextInt(1, 4);
                if (type == 1 || type == 2 || type == 3) {
                    count--;
                    arr[type]++;
                    if (arr[1] >= (18 + (level + 1) % 10 * 2 + Enemy)) return 1;
                    if (arr[2] >= (13 + (level + 1) % 10 * 2 + Enemy)) return 2;
                    if (arr[3] >= (10 + (level + 1) % 10 * 2 + Enemy)) return 3;
                }
            }
        }
        return 2;
    }
}