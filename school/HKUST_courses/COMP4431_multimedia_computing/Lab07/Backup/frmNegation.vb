Public Class frmNegation
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
    Friend WithEvents gbxRGB As System.Windows.Forms.GroupBox
    Public WithEvents chkRed As System.Windows.Forms.CheckBox
    Public WithEvents chkGreen As System.Windows.Forms.CheckBox
    Public WithEvents chkBlue As System.Windows.Forms.CheckBox
    Friend WithEvents btnApply As System.Windows.Forms.Button
    Friend WithEvents gbxHSV As System.Windows.Forms.GroupBox
    Public WithEvents chkValue As System.Windows.Forms.CheckBox
    Public WithEvents chkSaturation As System.Windows.Forms.CheckBox
    Public WithEvents chkHue As System.Windows.Forms.CheckBox
    Friend WithEvents lblPlanes As System.Windows.Forms.Label
    Friend WithEvents cboSystem As System.Windows.Forms.ComboBox
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.gbxRGB = New System.Windows.Forms.GroupBox
        Me.chkBlue = New System.Windows.Forms.CheckBox
        Me.chkGreen = New System.Windows.Forms.CheckBox
        Me.chkRed = New System.Windows.Forms.CheckBox
        Me.btnApply = New System.Windows.Forms.Button
        Me.gbxHSV = New System.Windows.Forms.GroupBox
        Me.chkValue = New System.Windows.Forms.CheckBox
        Me.chkSaturation = New System.Windows.Forms.CheckBox
        Me.chkHue = New System.Windows.Forms.CheckBox
        Me.cboSystem = New System.Windows.Forms.ComboBox
        Me.lblPlanes = New System.Windows.Forms.Label
        Me.gbxRGB.SuspendLayout()
        Me.gbxHSV.SuspendLayout()
        Me.SuspendLayout()
        '
        'gbxRGB
        '
        Me.gbxRGB.Controls.Add(Me.chkBlue)
        Me.gbxRGB.Controls.Add(Me.chkGreen)
        Me.gbxRGB.Controls.Add(Me.chkRed)
        Me.gbxRGB.Location = New System.Drawing.Point(12, 42)
        Me.gbxRGB.Name = "gbxRGB"
        Me.gbxRGB.Size = New System.Drawing.Size(270, 50)
        Me.gbxRGB.TabIndex = 0
        Me.gbxRGB.TabStop = False
        Me.gbxRGB.Text = "RGB Planes"
        '
        'chkBlue
        '
        Me.chkBlue.Checked = True
        Me.chkBlue.CheckState = System.Windows.Forms.CheckState.Checked
        Me.chkBlue.Location = New System.Drawing.Point(176, 19)
        Me.chkBlue.Name = "chkBlue"
        Me.chkBlue.Size = New System.Drawing.Size(70, 21)
        Me.chkBlue.TabIndex = 2
        Me.chkBlue.Text = "Blue"
        '
        'chkGreen
        '
        Me.chkGreen.Checked = True
        Me.chkGreen.CheckState = System.Windows.Forms.CheckState.Checked
        Me.chkGreen.Location = New System.Drawing.Point(94, 19)
        Me.chkGreen.Name = "chkGreen"
        Me.chkGreen.Size = New System.Drawing.Size(66, 21)
        Me.chkGreen.TabIndex = 1
        Me.chkGreen.Text = "Green"
        '
        'chkRed
        '
        Me.chkRed.Checked = True
        Me.chkRed.CheckState = System.Windows.Forms.CheckState.Checked
        Me.chkRed.Location = New System.Drawing.Point(12, 19)
        Me.chkRed.Name = "chkRed"
        Me.chkRed.Size = New System.Drawing.Size(49, 20)
        Me.chkRed.TabIndex = 0
        Me.chkRed.Text = "Red"
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(107, 164)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(80, 24)
        Me.btnApply.TabIndex = 1
        Me.btnApply.Text = "Apply"
        '
        'gbxHSV
        '
        Me.gbxHSV.Controls.Add(Me.chkValue)
        Me.gbxHSV.Controls.Add(Me.chkSaturation)
        Me.gbxHSV.Controls.Add(Me.chkHue)
        Me.gbxHSV.Enabled = False
        Me.gbxHSV.Location = New System.Drawing.Point(12, 102)
        Me.gbxHSV.Name = "gbxHSV"
        Me.gbxHSV.Size = New System.Drawing.Size(270, 50)
        Me.gbxHSV.TabIndex = 3
        Me.gbxHSV.TabStop = False
        Me.gbxHSV.Text = "HSV Planes"
        '
        'chkValue
        '
        Me.chkValue.Checked = True
        Me.chkValue.CheckState = System.Windows.Forms.CheckState.Checked
        Me.chkValue.Location = New System.Drawing.Point(176, 19)
        Me.chkValue.Name = "chkValue"
        Me.chkValue.Size = New System.Drawing.Size(70, 21)
        Me.chkValue.TabIndex = 2
        Me.chkValue.Text = "Value"
        '
        'chkSaturation
        '
        Me.chkSaturation.Checked = True
        Me.chkSaturation.CheckState = System.Windows.Forms.CheckState.Checked
        Me.chkSaturation.Location = New System.Drawing.Point(94, 19)
        Me.chkSaturation.Name = "chkSaturation"
        Me.chkSaturation.Size = New System.Drawing.Size(83, 21)
        Me.chkSaturation.TabIndex = 1
        Me.chkSaturation.Text = "Saturation"
        '
        'chkHue
        '
        Me.chkHue.Checked = True
        Me.chkHue.CheckState = System.Windows.Forms.CheckState.Checked
        Me.chkHue.Location = New System.Drawing.Point(12, 19)
        Me.chkHue.Name = "chkHue"
        Me.chkHue.Size = New System.Drawing.Size(49, 20)
        Me.chkHue.TabIndex = 0
        Me.chkHue.Text = "Hue"
        '
        'cboSystem
        '
        Me.cboSystem.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.cboSystem.FormattingEnabled = True
        Me.cboSystem.Items.AddRange(New Object() {"RGB Planes", "HSV Planes"})
        Me.cboSystem.Location = New System.Drawing.Point(133, 12)
        Me.cboSystem.Name = "cboSystem"
        Me.cboSystem.Size = New System.Drawing.Size(149, 21)
        Me.cboSystem.TabIndex = 4
        '
        'lblPlanes
        '
        Me.lblPlanes.AutoSize = True
        Me.lblPlanes.Location = New System.Drawing.Point(12, 15)
        Me.lblPlanes.Name = "lblPlanes"
        Me.lblPlanes.Size = New System.Drawing.Size(115, 13)
        Me.lblPlanes.TabIndex = 5
        Me.lblPlanes.Text = "Negation is applied on:"
        '
        'frmNegation
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(294, 200)
        Me.Controls.Add(Me.lblPlanes)
        Me.Controls.Add(Me.cboSystem)
        Me.Controls.Add(Me.gbxHSV)
        Me.Controls.Add(Me.btnApply)
        Me.Controls.Add(Me.gbxRGB)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmNegation"
        Me.Text = "Negation"
        Me.gbxRGB.ResumeLayout(False)
        Me.gbxHSV.ResumeLayout(False)
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

#End Region

    Private Sub frmNegation_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        cboSystem.SelectedIndex = 0
    End Sub

    Private Sub cboSystem_SelectedIndexChanged(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles cboSystem.SelectedIndexChanged
        gbxRGB.Enabled = (cboSystem.SelectedIndex = 0)
        gbxHSV.Enabled = Not (cboSystem.SelectedIndex = 0)
    End Sub

    Private Sub btnApply_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnApply.Click
        If (cboSystem.SelectedIndex = 0 And (chkRed.Checked Or chkGreen.Checked Or chkBlue.Checked)) Or _
           (cboSystem.SelectedIndex = 1 And (chkHue.Checked Or chkSaturation.Checked Or chkValue.Checked)) Then
            ApplyEffect()
        End If
    End Sub

    Overrides Function Process(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color
        If cboSystem.SelectedIndex = 0 Then
            Return Negation(image.GetPixel(x, y), chkRed.Checked, chkGreen.Checked, chkBlue.Checked, 0)
        Else
            Return Negation(image.GetPixel(x, y), chkHue.Checked, chkSaturation.Checked, chkValue.Checked, 1)
        End If
    End Function
End Class
