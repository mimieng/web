// 轮播图功能
document.addEventListener('DOMContentLoaded', function() {
    // 获取轮播元素
    const slides = document.querySelectorAll('.slide');
    const dots = document.querySelectorAll('.dot');
    const prevBtn = document.querySelector('.prev');
    const nextBtn = document.querySelector('.next');
    
    let currentSlide = 0;
    const slideCount = slides.length;
    
    // 显示指定幻灯片
    function showSlide(index) {
        // 隐藏所有幻灯片
        slides.forEach(slide => {
            slide.classList.remove('active');
        });
        
        // 移除所有点的活动状态
        dots.forEach(dot => {
            dot.classList.remove('active');
        });
        
        // 显示当前幻灯片和点
        slides[index].classList.add('active');
        dots[index].classList.add('active');
        
        // 更新当前幻灯片索引
        currentSlide = index;
    }
    
    // 下一张幻灯片
    function nextSlide() {
        let next = currentSlide + 1;
        if (next >= slideCount) {
            next = 0;
        }
        showSlide(next);
    }
    
    // 上一张幻灯片
    function prevSlide() {
        let prev = currentSlide - 1;
        if (prev < 0) {
            prev = slideCount - 1;
        }
        showSlide(prev);
    }
    
    // 点击事件
    if (prevBtn) prevBtn.addEventListener('click', prevSlide);
    if (nextBtn) nextBtn.addEventListener('click', nextSlide);
    
    // 点击点切换幻灯片
    dots.forEach((dot, index) => {
        dot.addEventListener('click', () => {
            showSlide(index);
        });
    });
    
    // 自动轮播
    setInterval(nextSlide, 5000);
});