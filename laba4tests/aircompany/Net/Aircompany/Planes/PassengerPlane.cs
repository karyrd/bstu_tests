using System;

namespace Aircompany.Planes
{
    public class PassengerPlane : Plane
    {
        public int _passengersCapacity;

        public PassengerPlane(string model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, int passengersCapacity)
            :base(model, maxSpeed, maxFlightDistance, maxLoadCapacity)
        {
            _passengersCapacity = passengersCapacity;
        }

        public override bool Equals(object obj)
        {
            return base.Equals(obj);
        }

        public override int GetHashCode()
        {
            return base.GetHashCode() + _passengersCapacity.GetHashCode();
        }

        public int PassengersCapacityIs()
        {
            return _passengersCapacity;
        }

       
        public override string ToString()
        {
            return base.ToString().Replace("}",
                    ", passengersCapacity=" + _passengersCapacity +
                    '}');
        }       
        
    }
}
