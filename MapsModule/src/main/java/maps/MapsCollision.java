package maps;

public class MapsCollision {
    class A {
        int a;
        int b;
        int c;

        public A(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object obj) {
            boolean result = true;
            if (this != obj) {
                if (obj instanceof A) {
                    A cla = (A) obj;
                    result = a == cla.a
                            && b == cla.b
                            && c == cla.c;
                }
            }
            return result;
        }

        @Override
        public int hashCode() {
            return a + b + c;
        }
    }
}