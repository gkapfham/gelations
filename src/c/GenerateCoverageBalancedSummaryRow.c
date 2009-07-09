#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

#define MAX_TESTS_NUM 100
#define MAX_REQS_NUM 1000

typedef char int8;
typedef short int int16;
typedef int int32;
typedef unsigned int uint32;

int8 coverage[MAX_REQS_NUM][MAX_TESTS_NUM];
uint32 overlap[MAX_REQS_NUM];
uint32 testreq[MAX_TESTS_NUM];
uint32 testnum, reqnum;
uint32 tot;
struct timeval my_time;

void output();

void init()
{
   bzero(coverage, MAX_REQS_NUM*MAX_TESTS_NUM);
   bzero(overlap, MAX_REQS_NUM);
   bzero(testreq, MAX_TESTS_NUM);
   
   gettimeofday(&my_time, (struct timeval*)0);
   
   srand48(my_time.tv_usec);
}


int gen()
{
   uint32 i,j,k, l, r, reqid, testid;
   uint32 rest;
   double prob;
   
   rest = tot;
   for (j = 0; j< testnum; j++) {
      k = drand48()* reqnum;
      coverage[k][j] = 1;
      testreq[j] ++;
      overlap[k] ++;
      rest --;
   }
  

 restart:
   r = drand48() * testnum;
   for (i = 0; i < reqnum; i++) {
      l = drand48() * reqnum;
      for (j = 0; j< testnum; j++) {
         testid = (j + l )%testnum;
         reqid =  (i + r) % reqnum;
         if (coverage[reqid][testid] == 0) {
            prob = drand48();
            if (prob > 0.5){
               coverage[reqid][testid] = 1;
               overlap[reqid] ++;
               testreq[testid] ++;
               rest --;
            }
         }
         if (rest == 0 ) 
            goto finish;
      }
   }
   
   if (rest > 0)
      goto restart;
   
    
 finish:  
   //output();
   i = 0;
   while (i < reqnum) {
      if (overlap[i] == 0) {
         l = drand48() * testnum;
         for (j = 0; j< testnum; j++) {
            r = drand48() * reqnum;
            for (k = 0; k < reqnum; k++) {
               testid = (j + l )%testnum;
               reqid =  (k + r) % reqnum;
               if ((coverage[reqid][testid] == 1) && (overlap[reqid] > 1)) {
                  //swap these two
                  coverage[reqid][testid] = 0;
                  coverage[i][testid] = 1;
                  overlap[i] ++;
                  overlap[reqid] --;
                  goto again;
               }
            }
         }
      }
   again:
      i ++;
   }
 
}
       
void output()
{
   uint32 i, j;
   
   for (i=0; i < reqnum; i++) {
      for (j = 0; j < testnum; j++) {
         fprintf(stdout, "%u ", coverage[i][j]);
      }
      fprintf(stdout, "%u\n", overlap[i]);
   }
   for (j = 0; j < testnum; j ++){
      fprintf(stdout, "%u ", testreq[j]);
   }
   fprintf(stdout, "%u\n", tot);
}
 


int main(int argc, char **argv)
{
   uint32 max;
   
   if (argc < 4) {
      fprintf(stderr, "usage: gentest_fixtot reqnum, testnum, totcoverage \n");
      exit(0);
   }
   reqnum = atoi(argv[1]);
   if ((reqnum < 1) || (reqnum > MAX_REQS_NUM)) {
      fprintf(stderr, "requirement number should be between 1 and %u \n", MAX_REQS_NUM);
      exit(0);
   }
   
   testnum = atoi(argv[2]);
   if ((testnum < 1) || (testnum > MAX_TESTS_NUM)) {
      fprintf(stderr, "test number should be between 1 and %u \n", MAX_TESTS_NUM);
      exit(0);
   }
   
   if (testnum < reqnum)
      max = reqnum;
   else 
      max = testnum;

   tot = atoi(argv[3]);
   
   if ((tot < max) || (tot > reqnum * testnum)) {

      fprintf(stderr, "total coverage number should be between %u and %u \n", max, reqnum * testnum);
      exit(0);
   }
   
   init();
   gen();
   output();
   
}
