Public Class frmPosterization
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
    Friend WithEvents tbRed As System.Windows.Forms.TrackBar
    Friend WithEvents btnApply As System.Windows.Forms.Button
    Friend WithEvents lblWeak As System.Windows.Forms.Label
    Friend WithEvents lblMedium As System.Windows.Forms.Label
    Friend WithEvents lblStrong As System.Windows.Forms.Label
    Friend WithEvents gbxPosterization As System.Windows.Forms.GroupBox
    Public WithEvents chkRed As System.Windows.Forms.CheckBox
    Public WithEvents chkBlue As System.Windows.Forms.CheckBox
    Public WithEvents chkGreen As System.Windows.Forms.CheckBox
    Friend WithEvents tbBlue As System.Windows.Forms.TrackBar
    Friend WithEvents tbGreen As System.Windows.Forms.TrackBar
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.tbRed = New System.Windows.Forms.TrackBar
        Me.btnApply = New System.Windows.Forms.Button
        Me.lblWeak = New System.Windows.Forms.Label
        Me.lblMedium = New System.Windows.Forms.Label
        Me.lblStrong = New System.Windows.Forms.Label
        Me.gbxPosterization = New System.Windows.Forms.GroupBox
        Me.chkBlue = New System.Windows.Forms.CheckBox
        Me.chkGreen = New System.Windows.Forms.CheckBox
        Me.tbBlue = New System.Windows.Forms.TrackBar
        Me.tbGreen = New System.Windows.Forms.TrackBar
        Me.chkRed = New System.Windows.Forms.CheckBox
        CType(Me.tbRed, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.gbxPosterization.SuspendLayout()
        CType(Me.tbBlue, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.tbGreen, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'tbRed
        '
        Me.tbRed.LargeChange = 1
        Me.tbRed.Location = New System.Drawing.Point(78, 19)
        Me.tbRed.Maximum = 7
        Me.tbRed.Minimum = 1
        Me.tbRed.Name = "tbRed"
        Me.tbRed.Size = New System.Drawing.Size(180, 42)
        Me.tbRed.TabIndex = 0
        Me.tbRed.Value = 5
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(107, 198)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(80, 24)
        Me.btnApply.TabIndex = 2
        Me.btnApply.Text = "Apply"
        '
        'lblWeak
        '
        Me.lblWeak.AutoSize = True
        Me.lblWeak.Location = New System.Drawing.Point(72, 146)
        Me.lblWeak.Name = "lblWeak"
        Me.lblWeak.Size = New System.Drawing.Size(36, 13)
        Me.lblWeak.TabIndex = 3
        Me.lblWeak.Text = "Weak"
        '
        'lblMedium
        '
        Me.lblMedium.AutoSize = True
        Me.lblMedium.Location = New System.Drawing.Point(120, 146)
        Me.lblMedium.Name = "lblMedium"
        Me.lblMedium.Size = New System.Drawing.Size(44, 13)
        Me.lblMedium.TabIndex = 4
        Me.lblMedium.Text = "Medium"
        '
        'lblStrong
        '
        Me.lblStrong.AutoSize = True
        Me.lblStrong.Location = New System.Drawing.Point(224, 146)
        Me.lblStrong.Name = "lblStrong"
        Me.lblStrong.Size = New System.Drawing.Size(38, 13)
        Me.lblStrong.TabIndex = 5
        Me.lblStrong.Text = "Strong"
        '
        'gbxPosterization
        '
        Me.gbxPosterization.Controls.Add(Me.lblMedium)
        Me.gbxPosterization.Controls.Add(Me.lblWeak)
        Me.gbxPosterization.Controls.Add(Me.lblStrong)
        Me.gbxPosterization.Controls.Add(Me.chkBlue)
        Me.gbxPosterization.Controls.Add(Me.chkGreen)
        Me.gbxPosterization.Controls.Add(Me.tbBlue)
        Me.gbxPosterization.Controls.Add(Me.tbGreen)
        Me.gbxPosterization.Controls.Add(Me.chkRed)
        Me.gbxPosterization.Controls.Add(Me.tbRed)
        Me.gbxPosterization.Location = New System.Drawing.Point(12, 12)
        Me.gbxPosterization.Name = "gbxPosterization"
        Me.gbxPosterization.Size = New System.Drawing.Size(270, 174)
        Me.gbxPosterization.TabIndex = 7
        Me.gbxPosterization.TabStop = False
        Me.gbxPosterization.Text = "Strength of Posterization"
        '
        'chkBlue
        '
        Me.chkBlue.Checked = True
        Me.chkBlue.CheckState = System.Windows.Forms.CheckState.Checked
        Me.chkBlue.Location = New System.Drawing.Point(12, 119)
        Me.chkBlue.Name = "chkBlue"
        Me.chkBlue.Size = New System.Drawing.Size(53, 20)
        Me.chkBlue.TabIndex = 10
        Me.chkBlue.Text = "Blue"
        '
        'chkGreen
        '
        Me.chkGreen.Checked = True
        Me.chkGreen.CheckState = System.Windows.Forms.CheckState.Checked
        Me.chkGreen.Location = New System.Drawing.Point(12, 71)
        Me.chkGreen.Name = "chkGreen"
        Me.chkGreen.Size = New System.Drawing.Size(62, 21)
        Me.chkGreen.TabIndex = 9
        Me.chkGreen.Text = "Green"
        '
        'tbBlue
        '
        Me.tbBlue.LargeChange = 1
        Me.tbBlue.Location = New System.Drawing.Point(78, 115)
        Me.tbBlue.Maximum = 7
        Me.tbBlue.Minimum = 1
        Me.tbBlue.Name = "tbBlue"
        Me.tbBlue.Size = New System.Drawing.Size(180, 42)
        Me.tbBlue.TabIndex = 8
        Me.tbBlue.Value = 5
        '
        'tbGreen
        '
        Me.tbGreen.LargeChange = 1
        Me.tbGreen.Location = New System.Drawing.Point(78, 67)
        Me.tbGreen.Maximum = 7
        Me.tbGreen.Minimum = 1
        Me.tbGreen.Name = "tbGreen"
        Me.tbGreen.Size = New System.Drawing.Size(180, 42)
        Me.tbGreen.TabIndex = 7
        Me.tbGreen.Value = 5
        '
        'chkRed
        '
        Me.chkRed.Checked = True
        Me.chkRed.CheckState = System.Windows.Forms.CheckState.Checked
        Me.chkRed.Location = New System.Drawing.Point(12, 23)
        Me.chkRed.Name = "chkRed"
        Me.chkRed.Size = New System.Drawing.Size(53, 19)
        Me.chkRed.TabIndex = 6
        Me.chkRed.Text = "Red"
        '
        'frmPosterization
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(294, 234)
        Me.Controls.Add(Me.gbxPosterization)
        Me.Controls.Add(Me.btnApply)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmPosterization"
        Me.Text = "Posterization"
        CType(Me.tbRed, System.ComponentModel.ISupportInitialize).EndInit()
        Me.gbxPosterization.ResumeLayout(False)
        Me.gbxPosterization.PerformLayout()
        CType(Me.tbBlue, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.tbGreen, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub btnApply_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnApply.Click
        ApplyEffect()
    End Sub

    Overrides Function Process(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color
        Dim redBits As Integer = IIf(chkRed.Checked, tbRed.Value, 0)
        Dim greenBits As Integer = IIf(chkGreen.Checked, tbGreen.Value, 0)
        Dim blueBits As Integer = IIf(chkBlue.Checked, tbBlue.Value, 0)

        Return Posterization(image.GetPixel(x, y), redBits, greenBits, blueBits)
    End Function
End Class
