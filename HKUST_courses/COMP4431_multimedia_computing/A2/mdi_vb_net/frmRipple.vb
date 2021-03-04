Public Class frmRipple
    Inherits frmSpatialEffect
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
    Friend WithEvents gbxSine As System.Windows.Forms.GroupBox
    Friend WithEvents tbAmplitude As System.Windows.Forms.TrackBar
    Friend WithEvents lblAmplitude As System.Windows.Forms.Label
    Friend WithEvents lblCycle As System.Windows.Forms.Label
    Friend WithEvents tbCycle As System.Windows.Forms.TrackBar
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.gbxSine = New System.Windows.Forms.GroupBox()
        Me.lblCycle = New System.Windows.Forms.Label()
        Me.tbCycle = New System.Windows.Forms.TrackBar()
        Me.lblAmplitude = New System.Windows.Forms.Label()
        Me.tbAmplitude = New System.Windows.Forms.TrackBar()
        Me.btnApply = New System.Windows.Forms.Button()
        Me.gbxSine.SuspendLayout()
        CType(Me.tbCycle, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.tbAmplitude, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'gbxSine
        '
        Me.gbxSine.Controls.Add(Me.lblCycle)
        Me.gbxSine.Controls.Add(Me.tbCycle)
        Me.gbxSine.Controls.Add(Me.lblAmplitude)
        Me.gbxSine.Controls.Add(Me.tbAmplitude)
        Me.gbxSine.Location = New System.Drawing.Point(14, 14)
        Me.gbxSine.Name = "gbxSine"
        Me.gbxSine.Size = New System.Drawing.Size(324, 138)
        Me.gbxSine.TabIndex = 10
        Me.gbxSine.TabStop = False
        '
        'lblCycle
        '
        Me.lblCycle.AutoSize = True
        Me.lblCycle.Location = New System.Drawing.Point(14, 74)
        Me.lblCycle.Name = "lblCycle"
        Me.lblCycle.Size = New System.Drawing.Size(79, 17)
        Me.lblCycle.TabIndex = 6
        Me.lblCycle.Text = "Frequency:"
        '
        'tbCycle
        '
        Me.tbCycle.Location = New System.Drawing.Point(89, 70)
        Me.tbCycle.Minimum = 1
        Me.tbCycle.Name = "tbCycle"
        Me.tbCycle.Size = New System.Drawing.Size(221, 56)
        Me.tbCycle.TabIndex = 5
        Me.tbCycle.Value = 5
        '
        'lblAmplitude
        '
        Me.lblAmplitude.AutoSize = True
        Me.lblAmplitude.Location = New System.Drawing.Point(14, 25)
        Me.lblAmplitude.Name = "lblAmplitude"
        Me.lblAmplitude.Size = New System.Drawing.Size(74, 17)
        Me.lblAmplitude.TabIndex = 4
        Me.lblAmplitude.Text = "Amplitude:"
        '
        'tbAmplitude
        '
        Me.tbAmplitude.Location = New System.Drawing.Point(89, 22)
        Me.tbAmplitude.Maximum = 20
        Me.tbAmplitude.Minimum = 1
        Me.tbAmplitude.Name = "tbAmplitude"
        Me.tbAmplitude.Size = New System.Drawing.Size(221, 56)
        Me.tbAmplitude.TabIndex = 0
        Me.tbAmplitude.Value = 8
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(123, 183)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(96, 28)
        Me.btnApply.TabIndex = 8
        Me.btnApply.Text = "Apply"
        '
        'frmRipple
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(6, 15)
        Me.ClientSize = New System.Drawing.Size(379, 247)
        Me.Controls.Add(Me.gbxSine)
        Me.Controls.Add(Me.btnApply)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmRipple"
        Me.Text = "Ripple"
        Me.gbxSine.ResumeLayout(False)
        Me.gbxSine.PerformLayout()
        CType(Me.tbCycle, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.tbAmplitude, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region







    Private Sub btnApply_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnApply.Click
        ApplyEffect()
    End Sub

    Overrides Sub Process(ByRef input As Bitmap, ByRef output As Bitmap, ByVal rect As Rectangle)
        Ripple(input, output, rect, tbAmplitude.Value, tbCycle.Value)
    End Sub



End Class
