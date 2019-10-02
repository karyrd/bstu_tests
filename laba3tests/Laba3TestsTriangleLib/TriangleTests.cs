using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laba3TestsTriangle;

namespace Laba3TestsTriangleLib
{
    public class TriangleTests
    {
        [Test]
        public void TriangleExists()
        {
            Assert.IsTrue(TriangleMain.isTriangleValid(6, 7, 10));
        }
        [Test]
        public void TriangleExistsNot()
        {
            Assert.IsTrue(!TriangleMain.isTriangleValid(4, 5, 11));
        }
        [Test]
        public void TriangleWithZero()
        {
            Assert.IsTrue(!TriangleMain.isTriangleValid(6, 7, 0));
        }
        [Test]
        public void TriangleWithZeroAll()
        {
            Assert.IsTrue(!TriangleMain.isTriangleValid(0, 0, 0));
        }
        [Test]
        public void TriangleNegative()
        {
            Assert.IsTrue(!TriangleMain.isTriangleValid(6, -2, 10));
        }
        [Test]
        public void TriangleNegativeAll()
        {
            Assert.IsTrue(!TriangleMain.isTriangleValid(-6, -7, -10));
        }
        [Test]
        public void IsIsosceles()
        {
            Assert.IsTrue(TriangleMain.isTriangleValid(6, 6, 10));
        }
        [Test]
        public void IsEquilateral()
        {
            Assert.IsTrue(TriangleMain.isTriangleValid(6, 6, 6));
        }
        [Test]
        public void TriangleWithZeroAllButOne()
        {
            Assert.IsTrue(!TriangleMain.isTriangleValid(0, 0, 10));
        }
        [Test]
        public void TriangleNegativeAllButOne()
        {
            Assert.IsTrue(!TriangleMain.isTriangleValid(-6, 7, -10));
        }
    }
}
