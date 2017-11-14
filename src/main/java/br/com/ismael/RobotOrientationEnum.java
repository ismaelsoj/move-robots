package br.com.ismael;

public enum RobotOrientationEnum {

    N {

        @Override
        public RobotOrientationEnum toLeft() {
            return W;
        }

        @Override
        public RobotOrientationEnum toRight() {
            return E;
        }

    },
    S {

        @Override
        public RobotOrientationEnum toLeft() {
            return E;
        }

        @Override
        public RobotOrientationEnum toRight() {
            return W;
        }

    },
    E {

        @Override
        public RobotOrientationEnum toLeft() {
            return N;
        }

        @Override
        public RobotOrientationEnum toRight() {
            return S;
        }

    },
    W {

        @Override
        public RobotOrientationEnum toLeft() {
            return S;
        }

        @Override
        public RobotOrientationEnum toRight() {
            return N;
        }

    };

    public abstract RobotOrientationEnum toLeft();

    public abstract RobotOrientationEnum toRight();

}
