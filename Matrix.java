public class Matrix
{
    double[][] m_daaMatrix;
    
    /**
     * Constructs a matrix with the specified rows and columns
     * and sets each element to zero.
     * @param iRows: The amount of rows which the matrix shall have.
     * @param iColumns: The amount of columns which the matrix shall have.
     */
    public Matrix(int iRows, int iColumns)
    {
       m_daaMatrix = new double[iRows][];
       
       for(int iIndex = 0; iIndex < iRows; ++iIndex)
       {
           m_daaMatrix[iIndex] = new double[iColumns];
       }
       
       set(0);
    }
    
    /**
     * Constructs a matrix based on a two dimensional double array.
     * If the column amount vary per row, it will throw an error.
     */
    public Matrix(double[][] daaMatrix)
    {
        set(daaMatrix);
    }
    
    /**
     * This method adds another matrix to the current one.
     * @param matrix: The other matrix
     */
    public void add(Matrix matrix)
    {
        if(!checkEqualMatrixRC(matrix)) return;
        
        for(int iRowIndex = 0; iRowIndex < m_daaMatrix.length; ++iRowIndex)
        {
            for(int iColumnIndex = 0; iColumnIndex < m_daaMatrix[iRowIndex].length; ++iColumnIndex)
            {
                m_daaMatrix[iRowIndex][iColumnIndex] += matrix.getValue(iRowIndex, iColumnIndex);
            }
        }
    }
    
    /**
     * This methods adds a constant to the matrix.
     * @param dValue: The value
     */
    public void add(double dValue)
    {
        for(int iRowIndex = 0; iRowIndex < m_daaMatrix.length; ++iRowIndex)
        {
            for(int iColumnIndex = 0; iColumnIndex < m_daaMatrix[iRowIndex].length; ++iColumnIndex)
            {
                m_daaMatrix[iRowIndex][iColumnIndex] += dValue;
            }
        }
    }
    
    /**
     * Adds a value to a specific element.
     * @param iRow: The row in which the element is located.
     * @param iColumn: The column in which the element is located.
     * @param dValue: The value which shall get added.
     */
    public void add(int iRow, int iColumn, double dValue)
    {
        if(!ooBCheck(iRow, iColumn))
        {
            throw new Error(Util.sOOBAccess);
        }
        
        m_daaMatrix[iRow][iColumn] += dValue;
    }

    /**
     * This methods multiplies a scalar with the matrix.
     * @param dScalar: The scalar
     */
    public void multiply(double dScalar)
    {
        for(int iRowIndex = 0; iRowIndex < m_daaMatrix.length; ++iRowIndex)
        {
            for(int iColumnIndex = 0; iColumnIndex < m_daaMatrix[iRowIndex].length; ++iColumnIndex)
            {
                m_daaMatrix[iRowIndex][iColumnIndex] *= dScalar;
            }
        }
    }
    
    /**
     * Multiplies a value to a specific element.
     * @param iRow: The row in which the element is located.
     * @param iColumn: The column in which the element is located.
     * @param dValue: The value which shall get multiplied.
     */
    public void multiply(int iRow, int iColumn, double dValue)
    {
        if(!ooBCheck(iRow, iColumn))
        {
            throw new Error(Util.sOOBAccess);
        }
        
        m_daaMatrix[iRow][iColumn] *= dValue;
    }
    
    /**
     * This method multiplies the matrix with another matrix.
     * @param matrix: The other matrix
     */
    public void multiply(Matrix matrix)
    {    
        Matrix rowMatrix = null;
        Matrix columnMatrix = null;
        // Zeilenvektor finden
        if(matrix.getRowAmount() == getColumnAmount())
        {
            rowMatrix = matrix;
            columnMatrix = this;
        }
        
        if(matrix.getColumnAmount() == getRowAmount())
        {
            rowMatrix = this;
            columnMatrix = matrix;
        }
        
        // Falls inkompatibel; Exception
        if(rowMatrix == null)
        {
            throw new Error("You cannot multiply these matrices.");
        }
        
        Matrix resultMatrix = new Matrix(rowMatrix.getRowAmount(), columnMatrix.getColumnAmount());
         
        for(int i = 0; i < rowMatrix.getRowAmount(); ++i)
        {
            for(int j = 0; j < columnMatrix.getColumnAmount(); ++j)
            {
                for(int k = 0; k < columnMatrix.getRowAmount(); ++k)
                {
                    resultMatrix.add(i, j, rowMatrix.getValue(i, k) * columnMatrix.getValue(k, j));
                }
            }
        }
        
        set(resultMatrix.getMatrix());
    }
    
    /**
     * This method sets the matrix to a constant value.
     * That is useful for e.g. initialisation.
     * @param dValue: The value
     */
    public void set(double dValue)
    { 
        for(int iRowIndex = 0; iRowIndex < m_daaMatrix.length; ++iRowIndex)
        {
            for(int iColumnIndex = 0; iColumnIndex < m_daaMatrix[iRowIndex].length; ++iColumnIndex)
            {
                m_daaMatrix[iRowIndex][iColumnIndex] = dValue;
            }
        }
    }
    
    /**
     * Sets a value to a specific element.
     * @param iRow: The row in which the element is located.
     * @param iColumn: The column in which the element is located.
     * @param dValue: The value which shall get set.
     */
    public void set(int iRow, int iColumn, double dValue)
    {
        if(!ooBCheck(iRow, iColumn))
        {
            throw new Error(Util.sOOBAccess);
        }
        
        m_daaMatrix[iRow][iColumn] = dValue;
    }
    /**
     * This method sets the matrix to another "raw" matrix
     * @param daaMatrix: The "Raw" matrix
     */
    private void set(double[][] daaMatrix)
    {
       m_daaMatrix = new double[daaMatrix.length][];
       
       int iNewColumnAmount = 0;
       int iOldColumnAmount = 0;
       
       for(int iRowIndex = 0; iRowIndex < daaMatrix.length; ++iRowIndex)
       {
           // Every matrix must have a fix column amount
           // This may not vary per row and here a check
           // is implemented.
           iOldColumnAmount = iNewColumnAmount;
           iNewColumnAmount = daaMatrix[iRowIndex].length;
           
           if(iOldColumnAmount != iNewColumnAmount && iRowIndex > 0)
           {
               throw new Error(Util.sOOBAccess);
           }
           
           m_daaMatrix[iRowIndex] = new double[iNewColumnAmount];
           for(int iColumnIndex = 0; iColumnIndex < daaMatrix[iRowIndex].length; ++iColumnIndex)
           {
               m_daaMatrix[iRowIndex][iColumnIndex] = daaMatrix[iRowIndex][iColumnIndex];
           }
       }
    }

    /**
     * This method sets the matrix to another matrix
     * @param matrix: The other matrix
     */
    public void set(Matrix matrix)
    {
        set(matrix.getMatrix());
    }
    
    /**
     * This method returns an element from the matrix. If the
     * element is not inside the matrix an exception gets thrown.
     * @param iRow: The row of the element in the matrix
     * @param iColumn: The column of the element in the matrix.
     */
    public double getValue(int iRow, int iColumn)
    {
        if(!ooBCheck(iRow, iColumn))
        {
            throw new Error("You may not access a row out of bounds!");
        }
        
        return m_daaMatrix[iRow][iColumn];
    }
    
    /**
     * This method copies the content from/of the matrix
     * and returns it.
     * @return: The matrix
     */
    public double[][] getMatrix()
    {
        double[][] daaMatrixCopy = new double[m_daaMatrix.length][];
        
        for(int iRow = 0; iRow < m_daaMatrix.length; ++iRow)
        {
            daaMatrixCopy[iRow] = new double[m_daaMatrix.length];
            for(int iColumn = 0; iColumn < m_daaMatrix[iRow].length; ++iColumn)
            {
                daaMatrixCopy[iRow][iColumn] = getValue(iRow, iColumn);
            }
        }
        
        return daaMatrixCopy;
    }
    
    /**
     * This methods returns the row amount of the matrix.
     * If the matrix is null, the method will throw an exception.
     * @return: The row amount (m_daaMatrix.length)
     */
    public int getRowAmount()
    {
        if(m_daaMatrix == null)
        {
            throw new Error("The matrix has not been initialised yet.");
        }

        return m_daaMatrix.length;
    }
    
    /**
     * This method returns the column amount of the matrix.
     * If the matrix is null or does not contain any rows, the method will throw an exception.
     * @return: The column amount (m_daaMatrix[0].length)
     */
    public int getColumnAmount()
    {
        if(m_daaMatrix == null)
        {
            throw new Error("The matrix has not been initialised yet.");
        }
        
        if(m_daaMatrix.length == 0)
        {
            throw new Error("The matrix does not contain any rows and therefore no columns.");
        }
        
        return m_daaMatrix[0].length;
    }
    
    /**
     * Checks whether the user tries to access the matrix out of bounds.
     * @param iRow: The row which shall be accessed.
     * @param iColumn: The column which shall be accessed.
     */
    private boolean ooBCheck(int iRow, int iColumn)
    {
        // ooB check for the row
        if(m_daaMatrix.length < iRow)
        {
            return false;          
        }
        
        // ooB check for the column
        if(m_daaMatrix[iRow].length < iColumn)
        {
            return false;
        }
        
        return true;
    }
    
    /**
     * This method checks whether the matrices have the same amount
     * of rows and columns.
     * @param matrix: The other matrix
     */
    private boolean checkEqualMatrixRC(Matrix matrix)
    {
        if(matrix.getRowAmount() != getRowAmount())
        {
            return false;
        }
        
        if(matrix.getColumnAmount() != getColumnAmount())
        {
            return false;
        }
        
        return true;
    }
}