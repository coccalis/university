struct InputData {
    int n;
    float r;
    float *X;
    float *Y;
};

program EX_CALC_PROG {
    version EX_CALC_VERS {
        int INNER_PRODUCT(InputData) = 1;
        float AVG_VALUES(InputData) = 2;
        float VEC_PRODUCT(InputData) = 3;
    }= 1;
}= 0x23451111;
