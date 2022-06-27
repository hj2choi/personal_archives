Public Class frmEdge
    Inherits System.Windows.Forms.Form

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
    Friend WithEvents btnCancel As System.Windows.Forms.Button
    Friend WithEvents slrStrength As System.Windows.Forms.TrackBar
    Friend WithEvents Label1 As System.Windows.Forms.Label
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.btnApply = New System.Windows.Forms.Button
        Me.btnCancel = New System.Windows.Forms.Button
        Me.slrStrength = New System.Windows.Forms.TrackBar
        Me.Label1 = New System.Windows.Forms.Label
        CType(Me.slrStrength, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(160, 96)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(120, 25)
        Me.btnApply.TabIndex = 22
        Me.btnApply.Text = "Apply"
        '
        'btnCancel
        '
        Me.btnCancel.Location = New System.Drawing.Point(24, 96)
        Me.btnCancel.Name = "btnCancel"
        Me.btnCancel.Size = New System.Drawing.Size(120, 25)
        Me.btnCancel.TabIndex = 23
        Me.btnCancel.Text = "Cancel"
        '
        'slrStrength
        '
        Me.slrStrength.Location = New System.Drawing.Point(24, 40)
        Me.slrStrength.Maximum = 5
        Me.slrStrength.Minimum = 1
        Me.slrStrength.Name = "slrStrength"
        Me.slrStrength.Size = New System.Drawing.Size(264, 42)
        Me.slrStrength.TabIndex = 24
        Me.slrStrength.Value = 1
        '
        'Label1
        '
        Me.Label1.Location = New System.Drawing.Point(24, 16)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(104, 16)
        Me.Label1.TabIndex = 25
        Me.Label1.Text = "Strength"
        '
        'frmEdge
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(304, 134)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.slrStrength)
        Me.Controls.Add(Me.btnCancel)
        Me.Controls.Add(Me.btnApply)
        Me.Name = "frmEdge"
        Me.Text = "Edge"
        CType(Me.slrStrength, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

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
        fImageForm = DirectCast(Me.MdiParent, frmMain).selectedImageForm

        If Not fImageForm Is Nothing Then
            Edge(fImageForm, slrStrength.Value)
        End If
    End Sub

    Private Sub btnCancel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnCancel.Click
        Me.Close()
    End Sub
End Class
