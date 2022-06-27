Option Explicit On 
Public Class Form1
    Inherits System.Windows.Forms.Form

#Region "Windows Form Designer generated code "
    Public Sub New()

        MyBase.New()
        'This call is required by the Windows Form Designer.
        InitializeComponent()
    End Sub
    'Form overrides dispose to clean up the component list.
    Protected Overloads Overrides Sub Dispose(ByVal Disposing As Boolean)
        If Disposing Then
            If Not components Is Nothing Then
                components.Dispose()
            End If
        End If
        MyBase.Dispose(Disposing)
    End Sub
    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer
    Public ToolTip1 As System.Windows.Forms.ToolTip
    Private WithEvents vol As System.Windows.Forms.VScrollBar
    Public WithEvents _key_15 As System.Windows.Forms.CheckBox
    Public WithEvents _key_13 As System.Windows.Forms.CheckBox
    Public WithEvents _key_10 As System.Windows.Forms.CheckBox
    Public WithEvents _key_8 As System.Windows.Forms.CheckBox
    Public WithEvents _key_6 As System.Windows.Forms.CheckBox
    Public WithEvents _key_3 As System.Windows.Forms.CheckBox
    Public WithEvents _key_1 As System.Windows.Forms.CheckBox
    Public WithEvents _key_16 As System.Windows.Forms.CheckBox
    Public WithEvents _key_14 As System.Windows.Forms.CheckBox
    Public WithEvents _key_12 As System.Windows.Forms.CheckBox
    Public WithEvents _key_11 As System.Windows.Forms.CheckBox
    Public WithEvents _key_9 As System.Windows.Forms.CheckBox
    Public WithEvents _key_7 As System.Windows.Forms.CheckBox
    Public WithEvents _key_5 As System.Windows.Forms.CheckBox
    Public WithEvents _key_4 As System.Windows.Forms.CheckBox
    Public WithEvents _key_2 As System.Windows.Forms.CheckBox
    Public WithEvents _key_0 As System.Windows.Forms.CheckBox
    Private WithEvents Label1 As System.Windows.Forms.Label
    Public WithEvents _device_0 As System.Windows.Forms.MenuItem
    Public WithEvents _device_1 As System.Windows.Forms.MenuItem
    Public WithEvents _device_2 As System.Windows.Forms.MenuItem
    Public WithEvents _device_3 As System.Windows.Forms.MenuItem
    Public WithEvents _device_4 As System.Windows.Forms.MenuItem
    Public WithEvents _device_5 As System.Windows.Forms.MenuItem
    Public WithEvents _device_6 As System.Windows.Forms.MenuItem
    Public WithEvents _device_7 As System.Windows.Forms.MenuItem
    Public WithEvents _device_8 As System.Windows.Forms.MenuItem
    Public WithEvents _device_9 As System.Windows.Forms.MenuItem
    Public WithEvents _device_10 As System.Windows.Forms.MenuItem
    Public WithEvents midi_devices As System.Windows.Forms.MenuItem
    Public WithEvents _chan_0 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_1 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_2 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_3 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_4 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_5 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_6 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_7 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_8 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_9 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_10 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_11 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_12 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_13 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_14 As System.Windows.Forms.MenuItem
    Public WithEvents _chan_15 As System.Windows.Forms.MenuItem
    Public WithEvents ChannelOption As System.Windows.Forms.MenuItem
    Public WithEvents base As System.Windows.Forms.MenuItem
    Friend WithEvents btnOneNote As System.Windows.Forms.Button
    Friend WithEvents btnChord As System.Windows.Forms.Button
    Friend WithEvents txtStatus As System.Windows.Forms.Label
    Friend WithEvents TrackbarInstrument As System.Windows.Forms.TrackBar
    Friend WithEvents TrackbarPanning As System.Windows.Forms.TrackBar
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents Label4 As System.Windows.Forms.Label
    Friend WithEvents Label5 As System.Windows.Forms.Label
    Friend WithEvents Label6 As System.Windows.Forms.Label
    Public MainMenu1 As System.Windows.Forms.MainMenu
    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.vol = New System.Windows.Forms.VScrollBar()
        Me._key_15 = New System.Windows.Forms.CheckBox()
        Me._key_13 = New System.Windows.Forms.CheckBox()
        Me._key_10 = New System.Windows.Forms.CheckBox()
        Me._key_8 = New System.Windows.Forms.CheckBox()
        Me._key_6 = New System.Windows.Forms.CheckBox()
        Me._key_3 = New System.Windows.Forms.CheckBox()
        Me._key_1 = New System.Windows.Forms.CheckBox()
        Me._key_16 = New System.Windows.Forms.CheckBox()
        Me._key_14 = New System.Windows.Forms.CheckBox()
        Me._key_12 = New System.Windows.Forms.CheckBox()
        Me._key_11 = New System.Windows.Forms.CheckBox()
        Me._key_9 = New System.Windows.Forms.CheckBox()
        Me._key_7 = New System.Windows.Forms.CheckBox()
        Me._key_5 = New System.Windows.Forms.CheckBox()
        Me._key_4 = New System.Windows.Forms.CheckBox()
        Me._key_2 = New System.Windows.Forms.CheckBox()
        Me._key_0 = New System.Windows.Forms.CheckBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me._chan_0 = New System.Windows.Forms.MenuItem()
        Me._chan_1 = New System.Windows.Forms.MenuItem()
        Me._chan_2 = New System.Windows.Forms.MenuItem()
        Me._chan_3 = New System.Windows.Forms.MenuItem()
        Me._chan_4 = New System.Windows.Forms.MenuItem()
        Me._chan_5 = New System.Windows.Forms.MenuItem()
        Me._chan_6 = New System.Windows.Forms.MenuItem()
        Me._chan_7 = New System.Windows.Forms.MenuItem()
        Me._chan_8 = New System.Windows.Forms.MenuItem()
        Me._chan_9 = New System.Windows.Forms.MenuItem()
        Me._chan_10 = New System.Windows.Forms.MenuItem()
        Me._chan_11 = New System.Windows.Forms.MenuItem()
        Me._chan_12 = New System.Windows.Forms.MenuItem()
        Me._chan_13 = New System.Windows.Forms.MenuItem()
        Me._chan_14 = New System.Windows.Forms.MenuItem()
        Me._chan_15 = New System.Windows.Forms.MenuItem()
        Me._device_0 = New System.Windows.Forms.MenuItem()
        Me._device_1 = New System.Windows.Forms.MenuItem()
        Me._device_2 = New System.Windows.Forms.MenuItem()
        Me._device_3 = New System.Windows.Forms.MenuItem()
        Me._device_4 = New System.Windows.Forms.MenuItem()
        Me._device_5 = New System.Windows.Forms.MenuItem()
        Me._device_6 = New System.Windows.Forms.MenuItem()
        Me._device_7 = New System.Windows.Forms.MenuItem()
        Me._device_8 = New System.Windows.Forms.MenuItem()
        Me._device_9 = New System.Windows.Forms.MenuItem()
        Me._device_10 = New System.Windows.Forms.MenuItem()
        Me.MainMenu1 = New System.Windows.Forms.MainMenu(Me.components)
        Me.midi_devices = New System.Windows.Forms.MenuItem()
        Me.ChannelOption = New System.Windows.Forms.MenuItem()
        Me.base = New System.Windows.Forms.MenuItem()
        Me.btnOneNote = New System.Windows.Forms.Button()
        Me.btnChord = New System.Windows.Forms.Button()
        Me.txtStatus = New System.Windows.Forms.Label()
        Me.TrackbarInstrument = New System.Windows.Forms.TrackBar()
        Me.TrackbarPanning = New System.Windows.Forms.TrackBar()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label6 = New System.Windows.Forms.Label()
        CType(Me.TrackbarInstrument, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.TrackbarPanning, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'vol
        '
        Me.vol.LargeChange = 1
        Me.vol.Location = New System.Drawing.Point(0, 27)
        Me.vol.Maximum = 32767
        Me.vol.Name = "vol"
        Me.vol.Size = New System.Drawing.Size(20, 131)
        Me.vol.TabIndex = 17
        Me.vol.TabStop = True
        '
        '_key_15
        '
        Me._key_15.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_15.BackColor = System.Drawing.Color.Black
        Me._key_15.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_15.ForeColor = System.Drawing.Color.White
        Me._key_15.Location = New System.Drawing.Point(365, 0)
        Me._key_15.Name = "_key_15"
        Me._key_15.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_15.Size = New System.Drawing.Size(20, 97)
        Me._key_15.TabIndex = 16
        Me._key_15.Text = ";"
        Me._key_15.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_15.UseVisualStyleBackColor = False
        '
        '_key_13
        '
        Me._key_13.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_13.BackColor = System.Drawing.Color.Black
        Me._key_13.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_13.ForeColor = System.Drawing.Color.White
        Me._key_13.Location = New System.Drawing.Point(326, 0)
        Me._key_13.Name = "_key_13"
        Me._key_13.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_13.Size = New System.Drawing.Size(21, 97)
        Me._key_13.TabIndex = 15
        Me._key_13.Text = "L"
        Me._key_13.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_13.UseVisualStyleBackColor = False
        '
        '_key_10
        '
        Me._key_10.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_10.BackColor = System.Drawing.Color.Black
        Me._key_10.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_10.ForeColor = System.Drawing.Color.White
        Me._key_10.Location = New System.Drawing.Point(250, 0)
        Me._key_10.Name = "_key_10"
        Me._key_10.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_10.Size = New System.Drawing.Size(20, 97)
        Me._key_10.TabIndex = 14
        Me._key_10.Text = "J"
        Me._key_10.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_10.UseVisualStyleBackColor = False
        '
        '_key_8
        '
        Me._key_8.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_8.BackColor = System.Drawing.Color.Black
        Me._key_8.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_8.ForeColor = System.Drawing.Color.White
        Me._key_8.Location = New System.Drawing.Point(211, 0)
        Me._key_8.Name = "_key_8"
        Me._key_8.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_8.Size = New System.Drawing.Size(21, 97)
        Me._key_8.TabIndex = 13
        Me._key_8.Text = "H"
        Me._key_8.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_8.UseVisualStyleBackColor = False
        '
        '_key_6
        '
        Me._key_6.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_6.BackColor = System.Drawing.Color.Black
        Me._key_6.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_6.ForeColor = System.Drawing.Color.White
        Me._key_6.Location = New System.Drawing.Point(173, 0)
        Me._key_6.Name = "_key_6"
        Me._key_6.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_6.Size = New System.Drawing.Size(20, 97)
        Me._key_6.TabIndex = 12
        Me._key_6.Text = "G"
        Me._key_6.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_6.UseVisualStyleBackColor = False
        '
        '_key_3
        '
        Me._key_3.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_3.BackColor = System.Drawing.Color.Black
        Me._key_3.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_3.ForeColor = System.Drawing.Color.White
        Me._key_3.Location = New System.Drawing.Point(96, 0)
        Me._key_3.Name = "_key_3"
        Me._key_3.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_3.Size = New System.Drawing.Size(20, 97)
        Me._key_3.TabIndex = 11
        Me._key_3.Text = "D"
        Me._key_3.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_3.UseVisualStyleBackColor = False
        '
        '_key_1
        '
        Me._key_1.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_1.BackColor = System.Drawing.Color.Black
        Me._key_1.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_1.ForeColor = System.Drawing.Color.White
        Me._key_1.Location = New System.Drawing.Point(58, 0)
        Me._key_1.Name = "_key_1"
        Me._key_1.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_1.Size = New System.Drawing.Size(20, 97)
        Me._key_1.TabIndex = 10
        Me._key_1.Text = "S"
        Me._key_1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_1.UseVisualStyleBackColor = False
        '
        '_key_16
        '
        Me._key_16.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_16.BackColor = System.Drawing.Color.White
        Me._key_16.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_16.ForeColor = System.Drawing.Color.Black
        Me._key_16.Location = New System.Drawing.Point(374, 0)
        Me._key_16.Name = "_key_16"
        Me._key_16.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_16.Size = New System.Drawing.Size(40, 158)
        Me._key_16.TabIndex = 9
        Me._key_16.Text = "/"
        Me._key_16.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_16.UseVisualStyleBackColor = False
        '
        '_key_14
        '
        Me._key_14.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_14.BackColor = System.Drawing.Color.White
        Me._key_14.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_14.ForeColor = System.Drawing.Color.Black
        Me._key_14.Location = New System.Drawing.Point(336, 0)
        Me._key_14.Name = "_key_14"
        Me._key_14.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_14.Size = New System.Drawing.Size(40, 158)
        Me._key_14.TabIndex = 8
        Me._key_14.Text = "."
        Me._key_14.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_14.UseVisualStyleBackColor = False
        '
        '_key_12
        '
        Me._key_12.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_12.BackColor = System.Drawing.Color.White
        Me._key_12.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_12.ForeColor = System.Drawing.Color.Black
        Me._key_12.Location = New System.Drawing.Point(298, 0)
        Me._key_12.Name = "_key_12"
        Me._key_12.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_12.Size = New System.Drawing.Size(39, 158)
        Me._key_12.TabIndex = 7
        Me._key_12.Text = ","
        Me._key_12.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_12.UseVisualStyleBackColor = False
        '
        '_key_11
        '
        Me._key_11.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_11.BackColor = System.Drawing.Color.White
        Me._key_11.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_11.ForeColor = System.Drawing.Color.Black
        Me._key_11.Location = New System.Drawing.Point(259, 0)
        Me._key_11.Name = "_key_11"
        Me._key_11.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_11.Size = New System.Drawing.Size(40, 158)
        Me._key_11.TabIndex = 6
        Me._key_11.Text = "M"
        Me._key_11.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_11.UseVisualStyleBackColor = False
        '
        '_key_9
        '
        Me._key_9.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_9.BackColor = System.Drawing.Color.White
        Me._key_9.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_9.ForeColor = System.Drawing.Color.Black
        Me._key_9.Location = New System.Drawing.Point(221, 0)
        Me._key_9.Name = "_key_9"
        Me._key_9.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_9.Size = New System.Drawing.Size(39, 158)
        Me._key_9.TabIndex = 5
        Me._key_9.Text = "N"
        Me._key_9.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_9.UseVisualStyleBackColor = False
        '
        '_key_7
        '
        Me._key_7.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_7.BackColor = System.Drawing.Color.White
        Me._key_7.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_7.ForeColor = System.Drawing.Color.Black
        Me._key_7.Location = New System.Drawing.Point(182, 0)
        Me._key_7.Name = "_key_7"
        Me._key_7.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_7.Size = New System.Drawing.Size(40, 158)
        Me._key_7.TabIndex = 4
        Me._key_7.Text = "B"
        Me._key_7.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_7.UseVisualStyleBackColor = False
        '
        '_key_5
        '
        Me._key_5.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_5.BackColor = System.Drawing.Color.White
        Me._key_5.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_5.ForeColor = System.Drawing.Color.Black
        Me._key_5.Location = New System.Drawing.Point(144, 0)
        Me._key_5.Name = "_key_5"
        Me._key_5.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_5.Size = New System.Drawing.Size(40, 158)
        Me._key_5.TabIndex = 3
        Me._key_5.Text = "V"
        Me._key_5.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_5.UseVisualStyleBackColor = False
        '
        '_key_4
        '
        Me._key_4.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_4.BackColor = System.Drawing.Color.White
        Me._key_4.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_4.ForeColor = System.Drawing.Color.Black
        Me._key_4.Location = New System.Drawing.Point(106, 0)
        Me._key_4.Name = "_key_4"
        Me._key_4.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_4.Size = New System.Drawing.Size(39, 158)
        Me._key_4.TabIndex = 2
        Me._key_4.Text = "C"
        Me._key_4.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_4.UseVisualStyleBackColor = False
        '
        '_key_2
        '
        Me._key_2.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_2.BackColor = System.Drawing.Color.White
        Me._key_2.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_2.ForeColor = System.Drawing.Color.Black
        Me._key_2.Location = New System.Drawing.Point(67, 0)
        Me._key_2.Name = "_key_2"
        Me._key_2.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_2.Size = New System.Drawing.Size(40, 158)
        Me._key_2.TabIndex = 1
        Me._key_2.Text = "X"
        Me._key_2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_2.UseVisualStyleBackColor = False
        '
        '_key_0
        '
        Me._key_0.Appearance = System.Windows.Forms.Appearance.Button
        Me._key_0.BackColor = System.Drawing.Color.White
        Me._key_0.Cursor = System.Windows.Forms.Cursors.Default
        Me._key_0.ForeColor = System.Drawing.Color.Black
        Me._key_0.Location = New System.Drawing.Point(29, 0)
        Me._key_0.Name = "_key_0"
        Me._key_0.RightToLeft = System.Windows.Forms.RightToLeft.No
        Me._key_0.Size = New System.Drawing.Size(39, 158)
        Me._key_0.TabIndex = 0
        Me._key_0.Text = "Z"
        Me._key_0.TextAlign = System.Drawing.ContentAlignment.MiddleCenter
        Me._key_0.UseVisualStyleBackColor = False
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(0, 9)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(26, 17)
        Me.Label1.TabIndex = 18
        Me.Label1.Text = "vol"
        '
        '_chan_0
        '
        Me._chan_0.Index = 0
        Me._chan_0.Text = "1"
        '
        '_chan_1
        '
        Me._chan_1.Index = 1
        Me._chan_1.Text = "2"
        '
        '_chan_2
        '
        Me._chan_2.Index = 2
        Me._chan_2.Text = "3"
        '
        '_chan_3
        '
        Me._chan_3.Index = 3
        Me._chan_3.Text = "4"
        '
        '_chan_4
        '
        Me._chan_4.Index = 4
        Me._chan_4.Text = "5"
        '
        '_chan_5
        '
        Me._chan_5.Index = 5
        Me._chan_5.Text = "6"
        '
        '_chan_6
        '
        Me._chan_6.Index = 6
        Me._chan_6.Text = "7"
        '
        '_chan_7
        '
        Me._chan_7.Index = 7
        Me._chan_7.Text = "8"
        '
        '_chan_8
        '
        Me._chan_8.Index = 8
        Me._chan_8.Text = "9"
        '
        '_chan_9
        '
        Me._chan_9.Index = 9
        Me._chan_9.Text = "10"
        '
        '_chan_10
        '
        Me._chan_10.Index = 10
        Me._chan_10.Text = "11"
        '
        '_chan_11
        '
        Me._chan_11.Index = 11
        Me._chan_11.Text = "12"
        '
        '_chan_12
        '
        Me._chan_12.Index = 12
        Me._chan_12.Text = "13"
        '
        '_chan_13
        '
        Me._chan_13.Index = 13
        Me._chan_13.Text = "14"
        '
        '_chan_14
        '
        Me._chan_14.Index = 14
        Me._chan_14.Text = "15"
        '
        '_chan_15
        '
        Me._chan_15.Index = 15
        Me._chan_15.Text = "16"
        '
        '_device_0
        '
        Me._device_0.Index = 0
        Me._device_0.Text = ""
        '
        '_device_1
        '
        Me._device_1.Enabled = False
        Me._device_1.Index = 1
        Me._device_1.Text = ""
        Me._device_1.Visible = False
        '
        '_device_2
        '
        Me._device_2.Enabled = False
        Me._device_2.Index = 2
        Me._device_2.Text = ""
        Me._device_2.Visible = False
        '
        '_device_3
        '
        Me._device_3.Enabled = False
        Me._device_3.Index = 3
        Me._device_3.Text = ""
        Me._device_3.Visible = False
        '
        '_device_4
        '
        Me._device_4.Enabled = False
        Me._device_4.Index = 4
        Me._device_4.Text = ""
        Me._device_4.Visible = False
        '
        '_device_5
        '
        Me._device_5.Enabled = False
        Me._device_5.Index = 5
        Me._device_5.Text = ""
        Me._device_5.Visible = False
        '
        '_device_6
        '
        Me._device_6.Enabled = False
        Me._device_6.Index = 6
        Me._device_6.Text = ""
        Me._device_6.Visible = False
        '
        '_device_7
        '
        Me._device_7.Enabled = False
        Me._device_7.Index = 7
        Me._device_7.Text = ""
        Me._device_7.Visible = False
        '
        '_device_8
        '
        Me._device_8.Enabled = False
        Me._device_8.Index = 8
        Me._device_8.Text = ""
        Me._device_8.Visible = False
        '
        '_device_9
        '
        Me._device_9.Enabled = False
        Me._device_9.Index = 9
        Me._device_9.Text = ""
        Me._device_9.Visible = False
        '
        '_device_10
        '
        Me._device_10.Enabled = False
        Me._device_10.Index = 10
        Me._device_10.Text = ""
        Me._device_10.Visible = False
        '
        'MainMenu1
        '
        Me.MainMenu1.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.midi_devices, Me.ChannelOption, Me.base})
        '
        'midi_devices
        '
        Me.midi_devices.Index = 0
        Me.midi_devices.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me._device_0, Me._device_1, Me._device_2, Me._device_3, Me._device_4, Me._device_5, Me._device_6, Me._device_7, Me._device_8, Me._device_9, Me._device_10})
        Me.midi_devices.Text = "Midi Device"
        '
        'ChannelOption
        '
        Me.ChannelOption.Index = 1
        Me.ChannelOption.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me._chan_0, Me._chan_1, Me._chan_2, Me._chan_3, Me._chan_4, Me._chan_5, Me._chan_6, Me._chan_7, Me._chan_8, Me._chan_9, Me._chan_10, Me._chan_11, Me._chan_12, Me._chan_13, Me._chan_14, Me._chan_15})
        Me.ChannelOption.Text = "Channel"
        '
        'base
        '
        Me.base.Index = 2
        Me.base.Text = "Base note"
        '
        'btnOneNote
        '
        Me.btnOneNote.Location = New System.Drawing.Point(442, 0)
        Me.btnOneNote.Name = "btnOneNote"
        Me.btnOneNote.Size = New System.Drawing.Size(140, 70)
        Me.btnOneNote.TabIndex = 19
        Me.btnOneNote.Text = "One note"
        Me.btnOneNote.UseVisualStyleBackColor = True
        '
        'btnChord
        '
        Me.btnChord.Location = New System.Drawing.Point(442, 86)
        Me.btnChord.Name = "btnChord"
        Me.btnChord.Size = New System.Drawing.Size(140, 72)
        Me.btnChord.TabIndex = 20
        Me.btnChord.Text = "Chord-Major"
        Me.btnChord.UseVisualStyleBackColor = True
        '
        'txtStatus
        '
        Me.txtStatus.AutoSize = True
        Me.txtStatus.Location = New System.Drawing.Point(29, 165)
        Me.txtStatus.Name = "txtStatus"
        Me.txtStatus.Size = New System.Drawing.Size(107, 17)
        Me.txtStatus.TabIndex = 21
        Me.txtStatus.Text = "status message"
        '
        'TrackbarInstrument
        '
        Me.TrackbarInstrument.LargeChange = 1
        Me.TrackbarInstrument.Location = New System.Drawing.Point(80, 194)
        Me.TrackbarInstrument.Maximum = 127
        Me.TrackbarInstrument.Name = "TrackbarInstrument"
        Me.TrackbarInstrument.Size = New System.Drawing.Size(502, 56)
        Me.TrackbarInstrument.TabIndex = 22
        '
        'TrackbarPanning
        '
        Me.TrackbarPanning.Location = New System.Drawing.Point(80, 256)
        Me.TrackbarPanning.Maximum = 127
        Me.TrackbarPanning.Name = "TrackbarPanning"
        Me.TrackbarPanning.Size = New System.Drawing.Size(502, 56)
        Me.TrackbarPanning.TabIndex = 23
        Me.TrackbarPanning.TickFrequency = 64
        Me.TrackbarPanning.Value = 64
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(12, 194)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(74, 17)
        Me.Label2.TabIndex = 24
        Me.Label2.Text = "Instrument"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(12, 256)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(60, 17)
        Me.Label3.TabIndex = 25
        Me.Label3.Text = "Panning"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(77, 295)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(32, 17)
        Me.Label4.TabIndex = 26
        Me.Label4.Text = "Left"
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(315, 295)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(50, 17)
        Me.Label5.TabIndex = 26
        Me.Label5.Text = "Center"
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Location = New System.Drawing.Point(550, 295)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(41, 17)
        Me.Label6.TabIndex = 26
        Me.Label6.Text = "Right"
        '
        'Form1
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(6, 15)
        Me.ClientSize = New System.Drawing.Size(616, 330)
        Me.Controls.Add(Me.Label6)
        Me.Controls.Add(Me.Label5)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.TrackbarPanning)
        Me.Controls.Add(Me.TrackbarInstrument)
        Me.Controls.Add(Me.txtStatus)
        Me.Controls.Add(Me.btnChord)
        Me.Controls.Add(Me.btnOneNote)
        Me.Controls.Add(Me.vol)
        Me.Controls.Add(Me._key_15)
        Me.Controls.Add(Me._key_13)
        Me.Controls.Add(Me._key_10)
        Me.Controls.Add(Me._key_8)
        Me.Controls.Add(Me._key_6)
        Me.Controls.Add(Me._key_3)
        Me.Controls.Add(Me._key_1)
        Me.Controls.Add(Me._key_16)
        Me.Controls.Add(Me._key_14)
        Me.Controls.Add(Me._key_12)
        Me.Controls.Add(Me._key_11)
        Me.Controls.Add(Me._key_9)
        Me.Controls.Add(Me._key_7)
        Me.Controls.Add(Me._key_5)
        Me.Controls.Add(Me._key_4)
        Me.Controls.Add(Me._key_2)
        Me.Controls.Add(Me._key_0)
        Me.Controls.Add(Me.Label1)
        Me.KeyPreview = True
        Me.Location = New System.Drawing.Point(11, 49)
        Me.Menu = Me.MainMenu1
        Me.Name = "Form1"
        Me.Text = "VB Midi Piano"
        CType(Me.TrackbarInstrument, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.TrackbarPanning, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
#End Region

    Const INVALID_NOTE As Short = -1 ' Code for keyboard keys that we don't handle

    Dim numDevices As Integer ' number of midi output devices
    Dim curDevice As Integer ' current midi device
    Dim hmidi As Integer ' midi output handle
    Dim rc As Integer ' return code
    Dim midimsg As Integer ' midi output message buffer
    Dim channel As Short ' midi output channel
    Dim volume As Short ' midi volume
    Dim baseNote As Short ' the first note on our "piano"

    Dim key As CheckBoxArray ' an array of check box for keys
    Dim chan As MenuItemArray ' an array of menu item for channel
    Dim device As MenuItemArray ' an array of menu item for midi device

    Dim playChord As Boolean

#Region "function initControlArray()"


    Public Sub initControlArray()
        ' initialize key checkboxarray (not generated by vb)
        key = New CheckBoxArray(Me)

        AddHandler _key_0.MouseDown, AddressOf key_MouseDown
        AddHandler _key_0.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_0)

        AddHandler _key_1.MouseDown, AddressOf key_MouseDown
        AddHandler _key_1.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_1)

        AddHandler _key_2.MouseDown, AddressOf key_MouseDown
        AddHandler _key_2.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_2)

        AddHandler _key_3.MouseDown, AddressOf key_MouseDown
        AddHandler _key_3.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_3)

        AddHandler _key_4.MouseDown, AddressOf key_MouseDown
        AddHandler _key_4.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_4)

        AddHandler _key_5.MouseDown, AddressOf key_MouseDown
        AddHandler _key_5.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_5)

        AddHandler _key_6.MouseDown, AddressOf key_MouseDown
        AddHandler _key_6.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_6)

        AddHandler _key_7.MouseDown, AddressOf key_MouseDown
        AddHandler _key_7.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_7)

        AddHandler _key_8.MouseDown, AddressOf key_MouseDown
        AddHandler _key_8.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_8)

        AddHandler _key_9.MouseDown, AddressOf key_MouseDown
        AddHandler _key_9.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_9)

        AddHandler _key_10.MouseDown, AddressOf key_MouseDown
        AddHandler _key_10.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_10)

        AddHandler _key_11.MouseDown, AddressOf key_MouseDown
        AddHandler _key_11.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_11)

        AddHandler _key_12.MouseDown, AddressOf key_MouseDown
        AddHandler _key_12.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_12)

        AddHandler _key_13.MouseDown, AddressOf key_MouseDown
        AddHandler _key_13.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_13)

        AddHandler _key_14.MouseDown, AddressOf key_MouseDown
        AddHandler _key_14.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_14)

        AddHandler _key_15.MouseDown, AddressOf key_MouseDown
        AddHandler _key_15.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_15)

        AddHandler _key_16.MouseDown, AddressOf key_MouseDown
        AddHandler _key_16.MouseUp, AddressOf key_MouseUp
        key.AddNewCheckBox(_key_16)

        ' initialize chan menuitemarray (not generated by vb)
        chan = New MenuItemArray(Me)
        AddHandler _chan_0.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_0)

        AddHandler _chan_1.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_1)

        AddHandler _chan_2.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_2)

        AddHandler _chan_3.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_3)

        AddHandler _chan_4.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_4)

        AddHandler _chan_5.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_5)

        AddHandler _chan_6.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_6)

        AddHandler _chan_7.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_7)

        AddHandler _chan_8.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_8)

        AddHandler _chan_9.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_9)

        AddHandler _chan_10.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_10)

        AddHandler _chan_11.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_11)

        AddHandler _chan_12.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_12)

        AddHandler _chan_13.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_13)

        AddHandler _chan_14.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_14)

        AddHandler _chan_15.Click, AddressOf chan_Click
        chan.AddNewMenuItem(_chan_15)

        ' initialize device menuitemarray (not generated by vb)
        device = New MenuItemArray(Me)

        AddHandler _device_0.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_0)

        AddHandler _device_1.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_1)

        AddHandler _device_2.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_2)

        AddHandler _device_3.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_3)

        AddHandler _device_4.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_4)

        AddHandler _device_5.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_5)

        AddHandler _device_6.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_6)

        AddHandler _device_7.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_7)

        AddHandler _device_8.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_8)

        AddHandler _device_9.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_9)

        AddHandler _device_10.Click, AddressOf device_Click
        device.AddNewMenuItem(_device_10)
    End Sub
#End Region

    Private Sub Form1_Load(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs) Handles MyBase.Load
        Dim i As Integer
        Dim caps As MIDIOUTCAPS

        Try
            initControlArray()

            ' Set the first device as midi mapper
            device(0).Text = "MIDI Mapper"
            device(0).Visible = True
            device(0).Enabled = True

            ' Get the rest of the midi devices
            numDevices = midiOutGetNumDevs()
            For i = 0 To (numDevices - 1)
                midiOutGetDevCaps(i, caps, Len(caps))
                device(i + 1).Text = caps.szPname
                device(i + 1).Visible = True
                device(i + 1).Enabled = True
            Next

            ' Select the MIDI Mapper as the default device
            device_Click(device.Item(0), New System.EventArgs)

            ' Set the default channel
            channel = 0
            chan(channel).Checked = True

            ' Set the base note
            baseNote = 60

            ' Set volume range
            volume = 127
            vol.Minimum = (0 + vol.LargeChange - 1)
            vol.Maximum = 127
            vol.Value = vol.Maximum - volume


        Catch ex As Exception

            ' do nothing

        End Try
    End Sub

    ' Set the value for the starting note of the piano
    Public Sub base_Click(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs) Handles base.Click
        Dim s As String
        Dim i As Short
        s = InputBox("Enter the new base note for the keyboard (0 - 111)", "Base note", CStr(baseNote))
        If IsNumeric(s) Then
            i = CShort(s)
            If (i >= 0 And i < 112) Then
                baseNote = i
            End If
        End If
    End Sub

    ' Select the midi output channel
    Public Sub chan_Click(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs)

        ' get the index of the menuitem clicked
        Dim index As Integer
        index = CType(eventSender, System.Windows.Forms.MenuItem).Index

        chan(channel).Checked = False
        channel = index
        chan(channel).Checked = True
    End Sub

    ' Open the midi device selected in the menu. The menu index equals the midi device number + 1.
    Public Sub device_Click(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs)

        ' get the index of the menuitem clicked
        Dim index As Integer
        index = CType(eventSender, System.Windows.Forms.MenuItem).Index

        device(curDevice + 1).Checked = False
        device(index).Checked = True
        curDevice = index - 1
        rc = midiOutClose(hmidi)
        rc = midiOutOpen(hmidi, curDevice, 0, 0, 0)
        If (rc <> 0) Then
            MessageBox.Show("Couldn't open midi out, rc = " & rc, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error)
        End If
    End Sub

    ' If user presses a keyboard key, start the corresponding midi note
    Private Sub Form1_KeyDown(ByVal eventSender As System.Object, ByVal eventArgs As System.Windows.Forms.KeyEventArgs) Handles MyBase.KeyDown
        Dim KeyCode As Short = eventArgs.KeyCode

        StartNote(NoteFromKey(KeyCode))
    End Sub

    ' If user lifts a keyboard key, stop the corresponding midi note
    Private Sub Form1_KeyUp(ByVal eventSender As System.Object, ByVal eventArgs As System.Windows.Forms.KeyEventArgs) Handles MyBase.KeyUp
        Dim KeyCode As Short = eventArgs.KeyCode

        StopNote(NoteFromKey(KeyCode))
    End Sub

    Private Sub Form1_Closed(ByVal eventSender As System.Object, ByVal eventArgs As System.EventArgs) Handles MyBase.Closed
        ' Close current midi device
        rc = midiOutClose(hmidi)
    End Sub

    ' Start a note when user click on it
    Public Sub key_MouseDown(ByVal eventSender As System.Object, ByVal eventArgs As System.Windows.Forms.MouseEventArgs)
        Dim index As Integer
        index = CType(eventSender, System.Windows.Forms.CheckBox).Tag
        StartNote((index))
    End Sub

    ' Stop the note when user lifts the mouse button
    Public Sub key_MouseUp(ByVal eventSender As System.Object, ByVal eventArgs As System.Windows.Forms.MouseEventArgs)
        Dim index As Integer
        index = CType(eventSender, System.Windows.Forms.CheckBox).Tag
        StopNote((index))
    End Sub

    ' Press the button and send midi start event
    Private Sub StartNote(ByRef Index As Short)
        If (Index = INVALID_NOTE) Then
            Exit Sub
        End If
        If (key(Index).CheckState = 1) Then
            Exit Sub
        End If

        key(Index).CheckState = System.Windows.Forms.CheckState.Checked
        midimsg = &H90 + ((baseNote + Index) * &H100) + (volume * &H10000) + channel
        midiOutShortMsg(hmidi, midimsg)
        txtStatus.Text = "instrument = " + (TrackbarInstrument.Value).ToString() + ", pitch = " + (baseNote + Index).ToString() + ", volume = " + volume.ToString() + ", channel = " + channel.ToString()
        If (playChord = True) Then
            midimsg = &H90 + ((baseNote + Index + 4) * &H100) + (volume * &H10000) + channel
            midiOutShortMsg(hmidi, midimsg)
            midimsg = &H90 + ((baseNote + Index + 7) * &H100) + (volume * &H10000) + channel
            midiOutShortMsg(hmidi, midimsg)

        End If
    End Sub

    ' Raise the button and send midi stop event
    Private Sub StopNote(ByRef Index As Short)
        If (Index = INVALID_NOTE) Then
            Exit Sub
        End If

        key(Index).CheckState = System.Windows.Forms.CheckState.Unchecked
        midimsg = &H80 + ((baseNote + Index) * &H100) + (volume * &H10000) + channel
        midiOutShortMsg(hmidi, midimsg)
        If (playChord = True) Then
            midimsg = &H80 + ((baseNote + Index + 4) * &H100) + (volume * &H10000) + channel
            midiOutShortMsg(hmidi, midimsg)
            midimsg = &H80 + ((baseNote + Index + 7) * &H100) + (volume * &H10000) + channel
            midiOutShortMsg(hmidi, midimsg)

        End If
    End Sub

    ' Get the note corresponding to a keyboard key
    Private Function NoteFromKey(ByRef key As Short) As Short

        NoteFromKey = INVALID_NOTE

        Select Case key
            Case System.Windows.Forms.Keys.Z
                NoteFromKey = 0
            Case System.Windows.Forms.Keys.S
                NoteFromKey = 1
            Case System.Windows.Forms.Keys.X
                NoteFromKey = 2
            Case System.Windows.Forms.Keys.D
                NoteFromKey = 3
            Case System.Windows.Forms.Keys.C
                NoteFromKey = 4
            Case System.Windows.Forms.Keys.V
                NoteFromKey = 5
            Case System.Windows.Forms.Keys.G
                NoteFromKey = 6
            Case System.Windows.Forms.Keys.B
                NoteFromKey = 7
            Case System.Windows.Forms.Keys.H
                NoteFromKey = 8
            Case System.Windows.Forms.Keys.N
                NoteFromKey = 9
            Case System.Windows.Forms.Keys.J
                NoteFromKey = 10
            Case System.Windows.Forms.Keys.M
                NoteFromKey = 11
            Case 188 ' comma
                NoteFromKey = 12
            Case System.Windows.Forms.Keys.L
                NoteFromKey = 13
            Case 190 ' period
                NoteFromKey = 14
            Case 186 ' semicolon
                NoteFromKey = 15
            Case 191 ' forward slash
                NoteFromKey = 16
        End Select

    End Function

    ' Set the volume
    Private Sub vol_Scroll(ByVal eventSender As System.Object, ByVal eventArgs As System.Windows.Forms.ScrollEventArgs) Handles vol.Scroll
        Select Case eventArgs.Type
            Case System.Windows.Forms.ScrollEventType.EndScroll
                volume = vol.Maximum - eventArgs.NewValue
        End Select
    End Sub

    Private Sub btnOneNote_Click(sender As Object, e As EventArgs) Handles btnOneNote.Click
        playChord = False
        txtStatus.Text = "play one note"
    End Sub

    Private Sub btnChord_Click(sender As Object, e As EventArgs) Handles btnChord.Click
        playChord = True
        txtStatus.Text = "play chord"
    End Sub

    Private Sub TrackbarInstrument_Scroll(sender As Object, e As EventArgs) Handles TrackbarInstrument.Scroll
        midimsg = &HC0 + ((TrackbarInstrument.Value) * &H100) + channel
        midiOutShortMsg(hmidi, midimsg)
        txtStatus.Text = "instrument changed to " + TrackbarInstrument.Value.ToString()
    End Sub

    Private Sub TrackbarPanning_Scroll(sender As Object, e As EventArgs) Handles TrackbarPanning.Scroll
        midimsg = &HB0 + channel + (&HA * &H100) + ((TrackbarPanning.Value) * &H10000)
        midiOutShortMsg(hmidi, midimsg)
        txtStatus.Text = "stereo positioning = " + (TrackbarInstrument.Value - 64).ToString() + " from the center"
    End Sub

End Class