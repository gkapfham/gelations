/* NOTE: The tests are organized as a column and the test requirements
   are organized along the rows.  From the example below you can see
   that there are two test cases and five requirements.  Also, the
   total number of coverages that we can plot are five.  This means
   that the algorithm will not place more than five ones insides of
   the entire coverage matrix. */

/* [gkapfham@massah coverage_effectiveness]$ ./gentest_fixtot */
/* usage: gentest_fixtot reqnum, testnum, totcoverage */

/* [gkapfham@massah coverage_effectiveness]$ ./gentest_fixtot 5 2 5 */
/* 0 1 1 */
/* 0 1 1 */
/* 0 1 1 */
/* 1 0 1 */
/* 1 0 1 */
/* 2 3 5 */

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
   uint32 rest, curreqnum;
   double prob;
   int32 min =1, max = reqnum;
   
   rest = tot;
   for (j = 0; j< testnum; j++) {
      if (rest - testnum + j + 1 < max)
         max = rest - testnum + j + 1;
      if ((testnum - j - 1)*reqnum < rest - min)
         min = rest - (testnum - j - 1)*reqnum ;
      testreq[j] = drand48() * (max - min + 1) + min;
      rest = rest - testreq[j];
      //   fprintf(stderr, "testreq %d %d %d %d %d\n", testreq[j],j, min, max);
      curreqnum = 0;
      while (curreqnum < testreq[j]) {
         k = drand48() * reqnum;
         if (coverage[k][j] == 0) {
            coverage[k][j] = 1;
            overlap[k] ++;
            curreqnum ++;
         }
      }
      //output();
   }
      

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
