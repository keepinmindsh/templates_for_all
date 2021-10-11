namespace sample_ui_socket_server
{
    partial class Form1
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            this.accessClient = new System.Windows.Forms.TextBox();
            this.failClient = new System.Windows.Forms.TextBox();
            this.messageFromClient = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // accessClient
            // 
            this.accessClient.Location = new System.Drawing.Point(13, 90);
            this.accessClient.Multiline = true;
            this.accessClient.Name = "accessClient";
            this.accessClient.Size = new System.Drawing.Size(373, 268);
            this.accessClient.TabIndex = 0;
            // 
            // failClient
            // 
            this.failClient.Location = new System.Drawing.Point(451, 90);
            this.failClient.Multiline = true;
            this.failClient.Name = "failClient";
            this.failClient.Size = new System.Drawing.Size(373, 268);
            this.failClient.TabIndex = 1;
            // 
            // messageFromClient
            // 
            this.messageFromClient.Location = new System.Drawing.Point(12, 397);
            this.messageFromClient.Multiline = true;
            this.messageFromClient.Name = "messageFromClient";
            this.messageFromClient.Size = new System.Drawing.Size(812, 268);
            this.messageFromClient.TabIndex = 2;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 75);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(93, 12);
            this.label1.TabIndex = 3;
            this.label1.Text = "접속 클라이언트";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(449, 75);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(121, 12);
            this.label2.TabIndex = 4;
            this.label2.Text = "접속 실패 클라이언트";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 382);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(169, 12);
            this.label3.TabIndex = 5;
            this.label3.Text = "클라이언트로부터 받은 메세지";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(846, 677);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.messageFromClient);
            this.Controls.Add(this.failClient);
            this.Controls.Add(this.accessClient);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox accessClient;
        private System.Windows.Forms.TextBox failClient;
        private System.Windows.Forms.TextBox messageFromClient;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
    }
}

