Option Strict Off

Public Class frmMain
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
    Public WithEvents mnuFileOpen As System.Windows.Forms.MenuItem
    Public WithEvents mnuFileSave As System.Windows.Forms.MenuItem
    Public WithEvents mnuFile As System.Windows.Forms.MenuItem
    Public WithEvents mnuEditSelectAll As System.Windows.Forms.MenuItem
    Public WithEvents mnuEdit As System.Windows.Forms.MenuItem
    Public WithEvents mnuView As System.Windows.Forms.MenuItem
    Public WithEvents mnuReset As System.Windows.Forms.MenuItem
    Public MainMenu1 As System.Windows.Forms.MainMenu
    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.
    'Do not modify it using the code editor.
    Friend WithEvents munExit As System.Windows.Forms.MenuItem
    Friend WithEvents mnuRoughen As System.Windows.Forms.MenuItem
    Friend WithEvents mnuEditSelectNone As System.Windows.Forms.MenuItem
    Friend WithEvents mnuHistogram As System.Windows.Forms.MenuItem
    Friend WithEvents mnuKernel As System.Windows.Forms.MenuItem
    Friend WithEvents mnuMelt As System.Windows.Forms.MenuItem
    Friend WithEvents mnuRotate As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem1 As System.Windows.Forms.MenuItem
    Friend WithEvents MenuItem2 As System.Windows.Forms.MenuItem
    Friend WithEvents mnuSpecial As System.Windows.Forms.MenuItem
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.MainMenu1 = New System.Windows.Forms.MainMenu(Me.components)
        Me.mnuFile = New System.Windows.Forms.MenuItem()
        Me.mnuFileOpen = New System.Windows.Forms.MenuItem()
        Me.mnuFileSave = New System.Windows.Forms.MenuItem()
        Me.munExit = New System.Windows.Forms.MenuItem()
        Me.mnuEdit = New System.Windows.Forms.MenuItem()
        Me.mnuEditSelectAll = New System.Windows.Forms.MenuItem()
        Me.mnuEditSelectNone = New System.Windows.Forms.MenuItem()
        Me.mnuView = New System.Windows.Forms.MenuItem()
        Me.mnuRoughen = New System.Windows.Forms.MenuItem()
        Me.mnuHistogram = New System.Windows.Forms.MenuItem()
        Me.mnuKernel = New System.Windows.Forms.MenuItem()
        Me.mnuMelt = New System.Windows.Forms.MenuItem()
        Me.mnuRotate = New System.Windows.Forms.MenuItem()
        Me.mnuSpecial = New System.Windows.Forms.MenuItem()
        Me.MenuItem1 = New System.Windows.Forms.MenuItem()
        Me.MenuItem2 = New System.Windows.Forms.MenuItem()
        Me.mnuReset = New System.Windows.Forms.MenuItem()
        Me.SuspendLayout()
        '
        'MainMenu1
        '
        Me.MainMenu1.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnuFile, Me.mnuEdit, Me.mnuView, Me.mnuReset})
        '
        'mnuFile
        '
        Me.mnuFile.Index = 0
        Me.mnuFile.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnuFileOpen, Me.mnuFileSave, Me.munExit})
        Me.mnuFile.MergeType = System.Windows.Forms.MenuMerge.Remove
        Me.mnuFile.Text = "&File"
        '
        'mnuFileOpen
        '
        Me.mnuFileOpen.Index = 0
        Me.mnuFileOpen.MergeType = System.Windows.Forms.MenuMerge.Remove
        Me.mnuFileOpen.Shortcut = System.Windows.Forms.Shortcut.CtrlO
        Me.mnuFileOpen.Text = "&Open"
        '
        'mnuFileSave
        '
        Me.mnuFileSave.Index = 1
        Me.mnuFileSave.MergeType = System.Windows.Forms.MenuMerge.Remove
        Me.mnuFileSave.Shortcut = System.Windows.Forms.Shortcut.CtrlS
        Me.mnuFileSave.Text = "&Save"
        '
        'munExit
        '
        Me.munExit.Index = 2
        Me.munExit.Text = "&Exit"
        '
        'mnuEdit
        '
        Me.mnuEdit.Index = 1
        Me.mnuEdit.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnuEditSelectAll, Me.mnuEditSelectNone})
        Me.mnuEdit.MergeType = System.Windows.Forms.MenuMerge.Remove
        Me.mnuEdit.Text = "&Edit"
        '
        'mnuEditSelectAll
        '
        Me.mnuEditSelectAll.Index = 0
        Me.mnuEditSelectAll.MergeType = System.Windows.Forms.MenuMerge.Remove
        Me.mnuEditSelectAll.Shortcut = System.Windows.Forms.Shortcut.CtrlA
        Me.mnuEditSelectAll.Text = "Select &All"
        '
        'mnuEditSelectNone
        '
        Me.mnuEditSelectNone.Index = 1
        Me.mnuEditSelectNone.Text = "Select None"
        '
        'mnuView
        '
        Me.mnuView.Index = 2
        Me.mnuView.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnuRoughen, Me.mnuHistogram, Me.mnuKernel, Me.mnuMelt, Me.mnuRotate, Me.mnuSpecial, Me.MenuItem1, Me.MenuItem2})
        Me.mnuView.MergeType = System.Windows.Forms.MenuMerge.Remove
        Me.mnuView.Text = "E&ffects"
        '
        'mnuRoughen
        '
        Me.mnuRoughen.Index = 0
        Me.mnuRoughen.Text = "&Roughen"
        '
        'mnuHistogram
        '
        Me.mnuHistogram.Index = 1
        Me.mnuHistogram.Text = "&Histogram"
        '
        'mnuKernel
        '
        Me.mnuKernel.Index = 2
        Me.mnuKernel.Text = "&Kernel"
        '
        'mnuMelt
        '
        Me.mnuMelt.Index = 3
        Me.mnuMelt.Text = "&Melt"
        '
        'mnuRotate
        '
        Me.mnuRotate.Index = 4
        Me.mnuRotate.Text = "Ro&tate"
        '
        'mnuSpecial
        '
        Me.mnuSpecial.Index = 5
        Me.mnuSpecial.Text = "BG removal"
        '
        'MenuItem1
        '
        Me.MenuItem1.Index = 6
        Me.MenuItem1.Text = "Edge Detection"
        '
        'MenuItem2
        '
        Me.MenuItem2.Index = 7
        Me.MenuItem2.Text = "Ripple"
        '
        'mnuReset
        '
        Me.mnuReset.Index = 3
        Me.mnuReset.MergeType = System.Windows.Forms.MenuMerge.Remove
        Me.mnuReset.Shortcut = System.Windows.Forms.Shortcut.CtrlR
        Me.mnuReset.Text = "&Reset"
        '
        'frmMain
        '
        Me.AutoScaleBaseSize = New System.Drawing.Size(5, 13)
        Me.ClientSize = New System.Drawing.Size(622, 443)
        Me.IsMdiContainer = True
        Me.Location = New System.Drawing.Point(4, 42)
        Me.Menu = Me.MainMenu1
        Me.Name = "frmMain"
        Me.Text = "Image Processing"
        Me.WindowState = System.Windows.Forms.FormWindowState.Maximized
        Me.ResumeLayout(False)

    End Sub
#End Region

    ' The currently selected image form
    Public selectedImageForm As frmImage

    Private Sub frmMain_Load(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MyBase.Load
        selectedImageForm = Nothing
    End Sub

    Public Sub mnuEditSelectAll_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuEditSelectAll.Click
        If Not selectedImageForm Is Nothing Then
            selectedImageForm.SelectAll()
        End If
    End Sub

    Private Sub mnuEditSelectNone_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuEditSelectNone.Click
        If Not selectedImageForm Is Nothing Then
            selectedImageForm.SelectNone()
        End If
    End Sub

    Public Sub mnuFileOpen_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuFileOpen.Click
        Dim dlg As New OpenFileDialog

        dlg.Filter = "Image Files (.bmp,.jpg,.png)|*.bmp;*.jpg;*.png"
        dlg.FilterIndex = 1
        dlg.RestoreDirectory = True

        If dlg.ShowDialog() = DialogResult.OK Then
            If Len(dlg.FileName) = 0 Then Exit Sub

            selectedImageForm = New frmImage
            selectedImageForm.MdiParent = Me
            If selectedImageForm.ShowPicture(dlg.FileName) Then
                selectedImageForm.Text = dlg.FileName
            End If
        End If
    End Sub


    Private Sub mnuRoughen_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuRoughen.Click
        Dim frmChild As New frmRoughen
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub mnuHistogram_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuHistogram.Click
        Dim frmChild As New frmHistogram
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub mnuKernel_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuKernel.Click
        Dim frmChild As New frmKernel
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub mnuMelt_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuMelt.Click
        Dim frmChild As New frmMelt
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub mnuRotate_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuRotate.Click
        Dim frmChild As New frmRotate
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub mnuSpecial_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuSpecial.Click
        Dim frmChild As New frmBgRemoval
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub MenuItem1_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MenuItem1.Click
        Dim frmChild As New frmEdge
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub MenuItem2_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles MenuItem2.Click
        Dim frmChild As New frmRipple
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub mnuFileSave_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuFileSave.Click
        If selectedImageForm Is Nothing Then Exit Sub

        Dim dlg As New SaveFileDialog

        dlg.Filter = "PNG Files (.png)|*.png|BMP Files (.bmp)|*.bmp|JPEG Files (.jpg)|*.jpg"
        dlg.FilterIndex = 1
        dlg.RestoreDirectory = True

        If dlg.ShowDialog() = DialogResult.OK Then
            If Len(dlg.FileName) = 0 Then Exit Sub

            Select Case dlg.FilterIndex
                Case 1
                    selectedImageForm.picImage.Image.Save(dlg.FileName, Imaging.ImageFormat.Png)
                Case 2
                    selectedImageForm.picImage.Image.Save(dlg.FileName, Imaging.ImageFormat.Bmp)
                Case 3
                    selectedImageForm.picImage.Image.Save(dlg.FileName, Imaging.ImageFormat.Jpeg)
            End Select
        End If
    End Sub

    Private Sub munExit_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles munExit.Click
        Me.Close()
    End Sub



 
End Class