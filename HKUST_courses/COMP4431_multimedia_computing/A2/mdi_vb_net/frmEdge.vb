Public Class frmEdge
    Inherits frmEffect

#Region " Windows Form Designer generated code "

    Public Sub New()
        MyBase.New()

        'This call is required by the Windows Form Designer.
        InitializeComponent()

        'Add any initialization after the InitializeComponent() call

    End Sub

    'Form overrides dispose to clean up the component list.
    Protected Overloads Overrides Sub Dispose(ByVal disposing As Boolean)
        If disposing Then
            If Not (components Is Nothing) Then
                components.Dispose()
            End If
        End If
        MyBase.Dispose(disposing)
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    Friend WithEvents btnApply As System.Windows.Forms.Button
    Friend WithEvents slrStrength As System.Windows.Forms.TrackBar
    Friend WithEvents chbNegation As System.Windows.Forms.CheckBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.slrStrength = New System.Windows.Forms.TrackBar()
        Me.btnApply = New System.Windows.Forms.Button()
        Me.chbNegation = New System.Windows.Forms.CheckBox()
        CType(Me.slrStrength, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'Label1
        '
        Me.Label1.Location = New System.Drawing.Point(29, 18)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(125, 19)
        Me.Label1.TabIndex = 25
        Me.Label1.Text = "Strength"
        '
        'slrStrength
        '
        Me.slrStrength.Location = New System.Drawing.Point(29, 46)
        Me.slrStrength.Maximum = 5
        Me.slrStrength.Minimum = 1
        Me.slrStrength.Name = "slrStrength"
        Me.slrStrength.Size = New System.Drawing.Size(317, 56)
        Me.slrStrength.TabIndex = 24
        Me.slrStrength.Value = 1
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(192, 111)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(144, 29)
        Me.btnApply.TabIndex = 22
        Me.btnApply.Text = "Apply"
        '
        'chbNegation
        '
        Me.chbNegation.AutoSize = True
        Me.chbNegation.Location = New System.Drawing.Point(41, 116)
        Me.chbNegation.Name = "chbNegation"
        Me.chbNegation.Size = New System.Drawing.Size(85, 21)
        Me.chbNegation.TabIndex = 26
        Me.chbNegation.Text = "negation"
        Me.chbNegation.UseVisualStyleBackColor = True
        '
        'frmEdge
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(6, 15)
        Me.ClientSize = New System.Drawing.Size(374, 164)
        Me.Controls.Add(Me.chbNegation)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.slrStrength)
        Me.Controls.Add(Me.btnApply)
        Me.Name = "frmEdge"
        Me.Text = "Edge"
        CType(Me.slrStrength, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

#End Region

    Private Sub btnApply_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnApply.Click
        Dim fImageForm As frmImage
        Dim X, Y As Short
        Dim PixelRGB As Color
        Dim imageBitmap As Bitmap
        Dim resultBitmap As Bitmap
        Dim imageHeight As Integer

        ' get the active image form
        'fImageForm = DirectCast(Me.MdiParent, frmMain).selectedImageForm

        'If Not fImageForm Is Nothing Then
        'Edge(fImageForm, slrStrength.Value)
        'End If
        ApplyEffect()
    End Sub

    Overrides Function Process(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color
        Return Edge(image, x, y, slrStrength.Value, chbNegation.Checked)
    End Function

    Private Sub btnCancel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs)
        Me.Close()
    End Sub
End Class
