public class Camera
{
    private float m_fFOV;
    private float m_fNearPlane;
    private float m_fFarPlane;


    public void setFOV(float fFOV)
    {
        m_fFOV = Math.abs(fFOV);
    }
    
    /**
     * Returns the current FOV.
     * 
     * @return: m_fFOV
     */
    public float getFOV()
    {
        return m_fFOV;
    }
    
    /**
     * Returns the defined near plane.
     * @return: m_fNearPlane
     */
    public float getNearPlane()
    {
        return m_fNearPlane;
    }
    
    /**
     * Returns the defined far plane.
     * @return: m_fFarPlane
     */
    public float getFarPlane()
    {
        return m_fFarPlane;
    }
}