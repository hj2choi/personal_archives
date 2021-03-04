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
    Public WithEvents mnuNegation As System.Windows.Forms.MenuItem
    Friend WithEvents munExit As System.Windows.Forms.MenuItem
    Friend WithEvents mnuGrayscale As System.Windows.Forms.MenuItem
    Friend WithEvents mnuBrightness As System.Windows.Forms.MenuItem
    Friend WithEvents mnuPosterization As System.Windows.Forms.MenuItem
    Friend WithEvents mnuRoughen As System.Windows.Forms.MenuItem
    Friend WithEvents mnuEditSelectNone As System.Windows.Forms.MenuItem
    Friend WithEvents mnuColorFill As System.Windows.Forms.MenuItem
    <System.Diagnostics.DebuggerStepThrough()> Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container
        Me.ToolTip1 = New System.Windows.Forms.ToolTip(Me.components)
        Me.MainMenu1 = New System.Windows.Forms.MainMenu(Me.components)
        Me.mnuFile = New System.Windows.Forms.MenuItem
        Me.mnuFileOpen = New System.Windows.Forms.MenuItem
        Me.mnuFileSave = New System.Windows.Forms.MenuItem
        Me.munExit = New System.Windows.Forms.MenuItem
        Me.mnuEdit = New System.Windows.Forms.MenuItem
        Me.mnuEditSelectAll = New System.Windows.Forms.MenuItem
        Me.mnuEditSelectNone = New System.Windows.Forms.MenuItem
        Me.mnuView = New System.Windows.Forms.MenuItem
        Me.mnuColorFill = New System.Windows.Forms.MenuItem
        Me.mnuNegation = New System.Windows.Forms.MenuItem
        Me.mnuGrayscale = New System.Windows.Forms.MenuItem
        Me.mnuBrightness = New System.Windows.Forms.MenuItem
        Me.mnuPosterization = New System.Windows.Forms.MenuItem
        Me.mnuRoughen = New System.Windows.Forms.MenuItem
        Me.mnuReset = New System.Windows.Forms.MenuItem
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
        Me.mnuView.MenuItems.AddRange(New System.Windows.Forms.MenuItem() {Me.mnuColorFill, Me.mnuNegation, Me.mnuGrayscale, Me.mnuBrightness, Me.mnuPosterization, Me.mnuRoughen})
        Me.mnuView.MergeType = System.Windows.Forms.MenuMerge.Remove
        Me.mnuView.Text = "E&ffects"
        '
        'mnuColorFill
        '
        Me.mnuColorFill.Index = 0
        Me.mnuColorFill.Text = "&Color Fill"
        '
        'mnuNegation
        '
        Me.mnuNegation.Index = 1
        Me.mnuNegation.Text = "&Negation"
        '
        'mnuGrayscale
        '
        Me.mnuGrayscale.Index = 2
        Me.mnuGrayscale.Text = "&Grayscale"
        '
        'mnuBrightness
        '
        Me.mnuBrightness.Index = 3
        Me.mnuBrightness.Text = "&Brightness"
        '
        'mnuPosterization
        '
        Me.mnuPosterization.Index = 4
        Me.mnuPosterization.Text = "&Posterization"
        '
        'mnuRoughen
        '
        Me.mnuRoughen.Index = 5
        Me.mnuRoughen.Text = "&Roughen"
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

    Public Sub mnuReset_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuReset.Click
        If Not selectedImageForm Is Nothing Then
            selectedImageForm.ResetPicture()
            selectedImageForm.DrawDashRect()
        End If
    End Sub

    Private Sub mnuColor_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuColorFill.Click
        Dim frmChild As New frmColorFill
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Public Sub mnuNegation_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuNegation.Click
        Dim frmChild As New frmNegation
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub mnuGrayScale_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuGrayscale.Click
        Dim frmChild As New frmGrayscale
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub mnuBrightness_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuBrightness.Click
        Dim frmChild As New frmBrightness
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub mnuPosterization_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuPosterization.Click
        Dim frmChild As New frmPosterization
        frmChild.MdiParent = Me
        frmChild.Show()
    End Sub

    Private Sub mnuRoughen_Click(ByVal sender As System.Object, ByVal e As System.EventArgs) Handles mnuRoughen.Click
        Dim frmChild As New frmRoughen
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