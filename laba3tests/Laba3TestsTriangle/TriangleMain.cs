using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laba3TestsTriangle
{
    public class TriangleMain
    {
        static void Main(string[] args)
        {

        }

        public static bool isTriangleValid(int side1len, int side2len, int side3len)
        {
            if(side1len <= 0 || side2len <= 0 || side3len <= 0)
            {
                return false;
            }
            return (side1len + side2len > side3len && 
                side2len + side3len > side1len && 
                side1len + side3len > side2len);
        }
    }
}
