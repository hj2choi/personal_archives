Public Class frmGhost
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
    Friend WithEvents gbxRoughen As System.Windows.Forms.GroupBox
    Friend WithEvents lblWeak As System.Windows.Forms.Label
    Friend WithEvents lblStrong As System.Windows.Forms.Label
    Friend WithEvents btnApply As System.Windows.Forms.Button
    Friend WithEvents tbThreshold As System.Windows.Forms.TrackBar
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.gbxRoughen = New System.Windows.Forms.GroupBox
        Me.lblWeak = New System.Windows.Forms.Label
        Me.lblStrong = New System.Windows.Forms.Label
        Me.tbThreshold = New System.Windows.Forms.TrackBar
        Me.btnApply = New System.Windows.Forms.Button
        Me.gbxRoughen.SuspendLayout()
        CType(Me.tbThreshold, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'gbxRoughen
        '
        Me.gbxRoughen.Controls.Add(Me.lblWeak)
        Me.gbxRoughen.Controls.Add(Me.lblStrong)
        Me.gbxRoughen.Controls.Add(Me.tbThreshold)
        Me.gbxRoughen.Location = New System.Drawing.Point(12, 12)
        Me.gbxRoughen.Name = "gbxRoughen"
        Me.gbxRoughen.Size = New System.Drawing.Size(270, 78)
        Me.gbxRoughen.TabIndex = 9
        Me.gbxRoughen.TabStop = False
        Me.gbxRoughen.Text = "Threshold"
        '
        'lblWeak
        '
        Me.lblWeak.AutoSize = True
        Me.lblWeak.Location = New System.Drawing.Point(19, 50)
        Me.lblWeak.Name = "lblWeak"
        Me.lblWeak.Size = New System.Drawing.Size(13, 13)
        Me.lblWeak.TabIndex = 3
        Me.lblWeak.Text = "0"
        '
        'lblStrong
        '
        Me.lblStrong.AutoSize = True
        Me.lblStrong.Location = New System.Drawing.Point(232, 50)
        Me.lblStrong.Name = "lblStrong"
        Me.lblStrong.Size = New System.Drawing.Size(25, 13)
        Me.lblStrong.TabIndex = 5
        Me.lblStrong.Text = "255"
        '
        'tbThreshold
        '
        Me.tbThreshold.LargeChange = 16
        Me.tbThreshold.Location = New System.Drawing.Point(12, 19)
        Me.tbThreshold.Maximum = 255
        Me.tbThreshold.Name = "tbThreshold"
        Me.tbThreshold.Size = New System.Drawing.Size(246, 42)
        Me.tbThreshold.TabIndex = 0
        Me.tbThreshold.TickFrequency = 16
        Me.tbThreshold.Value = 128
        '
        'btnApply
        '
        Me.btnApply.Location = New System.Drawing.Point(107, 102)
        Me.btnApply.Name = "btnApply"
        Me.btnApply.Size = New System.Drawing.Size(80, 24)
        Me.btnApply.TabIndex = 8
        Me.btnApply.Text = "Apply"
        '
        'frmGhost
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(294, 138)
        Me.Controls.Add(Me.btnApply)
        Me.Controls.Add(Me.gbxRoughen)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.Name = "frmGhost"
        Me.Text = "Ghost"
        Me.gbxRoughen.ResumeLayout(False)
        Me.gbxRoughen.PerformLayout()
        CType(Me.tbThreshold, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

#End Region

    Private Sub btnApply_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles btnApply.Click
        ApplyEffect()
    End Sub

    Overrides Function Process(ByRef image As Bitmap, ByVal x As Integer, ByVal y As Integer) As Color
        Return Ghost(image, x, y, tbThreshold.Value)
    End Function
End Class
