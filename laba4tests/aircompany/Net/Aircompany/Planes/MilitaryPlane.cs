using Aircompany.Models;

namespace Aircompany.Planes
{
    public class MilitaryPlane : Plane
    {
        public MilitaryType _type;

        public MilitaryPlane(string model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, MilitaryType type)
            : base(model, maxSpeed, maxFlightDistance, maxLoadCapacity)
        {
            _type = type;
        }

        public override bool Equals(object obj)
        {
            return base.Equals(obj);
        }

        public override int GetHashCode()
        {
            return base.GetHashCode() + _type.GetHashCode();
        }

        public MilitaryType PlaneTypeIs()
        {
            return _type;
        }


        public override string ToString()
        {
            return base.ToString().Replace("}",
                    ", type=" + _type +
                    '}');
        }        
    }
}
